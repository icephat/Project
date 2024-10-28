<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\URC_Employee;
use App\Models\URC_Department;
use App\Models\URC_Division;
use App\Models\URC_EmployeeClass;
use Illuminate\Support\Facades\DB;

class EmployeeController extends Controller
{
    public function create()
    {
        $departmentLists = URC_Department::all();
        $divisionLists = URC_Division::all();
        $classMuti = URC_EmployeeClass::select('ClassID', 'ClassNameTh')
            ->where('ClassID', '>', 1)
            ->get();


        return view('create', ['departmentLists' => $departmentLists, 'divisionLists' => $divisionLists, 'classMuti' => $classMuti]);
    }

    public function save(Request $request)
    {
        $validatedData = $request->validate([
            'EmployeeCode' => 'required|numeric|digits:6|unique:URC_Employee,EmployeeCode',
            'FirstName' => 'required|max:50|regex:/^[ก-๙เ-์]+$/',
            'LastName' => 'required|max:50|regex:/^[ก-๙เ-์]+$/',
            'FirstNameEN' => 'required|max:50|regex:/^[a-zA-Z]+$/',
            'LastNameEN' => 'required|max:50|regex:/^[a-zA-Z]+$/',
            'NickName' => 'required|max:50',
            'position' => 'required|max:50',
            'MobilePhone' => 'nullable',
            'Email' => 'required|max:150',
            'FreeEmail' => 'max:150|nullable',
            'Location' => 'required',
            'DepartmentCode' => 'required',
            'DivisionCode' => 'required',
            'ClassID' => 'required',
            'TelephoneInternal' => 'nullable',
            'WorkStartdate' => 'required',
        ]);

        $divisionCode = $validatedData['DivisionCode'];

        if ($divisionCode === '20') {
            $classID = 3;
        } else {
            $classID = $validatedData['ClassID'];
        }

        $departmentCode = $validatedData['DepartmentCode'];


        if ($validatedData['Location'] === 'URC3') {
            $bossCode = "500194";
        } else {

            $results = DB::table('URC_Division')
                ->join('URC_Employee', 'URC_Division.DivisionCode', '=', 'URC_Employee.DivisionCode')
                ->join('URC_EmployeeClass', 'URC_Employee.ClassID', '=', 'URC_EmployeeClass.ClassID')
                ->leftJoin('URC_Department', 'URC_Employee.DepartmentCode', '=', 'URC_Department.DepartmentCode')
                ->where(function ($query) use ($divisionCode) {
                    $query->where('URC_Division.DivisionCode', $divisionCode)
                        ->orWhere('URC_Division.DivisionCode', '10');
                })
                ->where('URC_EmployeeClass.ClassID', '<', $classID)
                ->where('URC_EmployeeClass.ClassID', '<>', 4)
                ->where(function ($query) use ($departmentCode) {
                    $query->whereNull('URC_Employee.DepartmentCode')
                        ->orWhere('URC_Employee.DepartmentCode', $departmentCode)
                        ->orWhere('URC_Employee.DepartmentCode', '');
                })
                ->orderBy('URC_EmployeeClass.ClassID', 'desc')
                ->select('URC_EmployeeClass.ClassID', 'URC_EmployeeClass.ClassNameTh', 'URC_Employee.EmployeeCode')
                ->get();

            $bossCode = $results[0]->EmployeeCode;
        }
		
		if ($divisionCode === '18') {
            $bossCode = "000032";
        } 
		
		
        $validatedData['BossCode'] = $bossCode;
        $validatedData['created_at'] = (new \DateTime())->setTime(0, 0)->format('Y-m-d H:i:s');
        $validatedData['updated_at'] = (new \DateTime())->setTime(0, 0)->format('Y-m-d H:i:s');
        $validatedData['EmployeeStatus'] = "ปกติ";
        $status = $validatedData['EmployeeStatus'];


        // ตรวจสอบว่ามีพนักงานที่มี EmployeeCode นี้อยู่แล้วหรือไม่
        if (URC_Employee::where('EmployeeCode', $validatedData['EmployeeCode'])->exists()) {
            // ถ้ามีอยู่แล้ว ส่งข้อความแจ้งเตือนกลับไปยังหน้าเดิม
            return redirect()->back()->withErrors(['EmployeeCode' => 'ข้อมูลพนักงานซ้ำ'])->withInput();
        }



        $employee = new URC_Employee($validatedData);
        $employee->save();

        // กลับไปยังหน้ารายการพนักงานหรือหน้าอื่นที่ต้องการ
        return redirect()->route('sendLine', ['EmployeeCode' => $employee->EmployeeCode, 'status' => $status])->with('success', 'เพิ่มพนักงานใหม่เรียบร้อยแล้ว');
    }

    public function findByEmployeeCode($EmployeeCode)
    {

        try {
            // ค้นหาข้อมูลพนักงานโดยใช้ EmployeeCode
            $employee = URC_Employee::where('EmployeeCode', $EmployeeCode)->first();

            // ค้นหาข้อมูลแผนกโดยใช้ departmentCode จากพนักงาน
            $department = URC_Department::where('DepartmentCode', $employee->DepartmentCode)->first();

            $classMuti = URC_EmployeeClass::select('ClassID', 'ClassNameTh')
                ->where('ClassID', '>', 1)
                ->get();

            $departmentLists = URC_Department::all();
            $divisionLists = URC_Division::all();

            // ส่งข้อมูลพนักงานและแผนกไปยัง view
            return view('detail', ['employee' => $employee, 'department' => $department, 'departmentLists' => $departmentLists, 'divisionLists' => $divisionLists, 'classMuti' => $classMuti]);
        } catch (\Exception $e) {
            // หากเกิดข้อผิดพลาดในการค้นหาข้อมูล
            return back()->with('error', 'Employee not found or Department not found');
        }
    }

    // public function findByEmployeeCodeAdmin($EmployeeCode)
    // {



    //         return view('create');

    // }

    public function update(Request $request, $EmployeeCode)
    {
        $validatedData = $request->validate([

            'EmployeeCode' => 'required|numeric|digits:6',
            'FirstName' => 'required|max:50|regex:/^[ก-๙เ-์]+$/',
            'LastName' => 'required|max:50|regex:/^[ก-๙เ-์]+$/',
            'FirstNameEN' => 'required|max:50|regex:/^[a-zA-Z]+$/',
            'LastNameEN' => 'required|max:50|regex:/^[a-zA-Z]+$/',
            'NickName' => 'required|max:50',
            'position' => 'required|max:50',
            'MobilePhone' => 'nullable',
            'Email' => 'required|max:150',
            'FreeEmail' => 'nullable|max:150',
            'Location' => 'required',
            'DepartmentCode' => 'required',
            'DivisionCode' => 'required',
            'ClassID' => 'required',
            'EmployeeStatus' => 'required|max:50',
            'TelephoneInternal' => 'nullable',
            'WorkStartdate' => 'required',
            'LeavDate' => 'nullable|date|required_if:EmployeeStatus,ลาออก', // กำหนดให้ LeavDate เป็น required ถ้า EmployeeStatus เป็น "ลาออก"

        ]);

        $employee2=[];

        $fields = [
            'EmployeeCode',
            'FirstName',
            'LastName',
            'FirstNameEN',
            'LastNameEN',
            'NickName',
            'position',
            'MobilePhone',
            'Email',
            'FreeEmail',
            'Location',
            'DepartmentCode',
            'DivisionCode',
            'ClassID',
            'TelephoneInternal',
        ];
        
        $employee = URC_Employee::where('EmployeeCode', $validatedData['EmployeeCode'])->first();
        foreach($fields as $field){

            if($employee->$field !== $validatedData[$field]){
                $employee2[$field] = $validatedData[$field];
                $employee->$field = $validatedData[$field];
            }

        }


        
        // $employee->EmployeeCode = $validatedData['EmployeeCode'];
        // $employee->FirstName = $validatedData['FirstName'];
        // $employee->LastName = $validatedData['LastName'];
        // $employee->FirstNameEN = $validatedData['FirstNameEN'];
        // $employee->LastNameEN = $validatedData['LastNameEN'];
        // $employee->NickName = $validatedData['NickName'];
        // $employee->position = $validatedData['position'];
        // $employee->MobilePhone = $validatedData['MobilePhone'];
        // $employee->Email = $validatedData['Email'];
        // $employee->FreeEmail = $validatedData['FreeEmail'];
        // $employee->TelephoneInternal = $validatedData['TelephoneInternal'];
        // $employee->Location = $validatedData['Location'];
        // $employee->DepartmentCode = $validatedData['DepartmentCode'];
        $employee->WorkStartdate = $validatedData['WorkStartdate'];
        $employee->LeavDate = $validatedData['LeavDate'] ?? null; // ตรวจสอบว่ามีค่า LeavDate หรือไม่
    
        

        if ($validatedData['EmployeeStatus'] === 'แก้ไข') {
            $employee->EmployeeStatus = "ปกติ";
        } else {
            $employee->EmployeeStatus = $validatedData['EmployeeStatus'];
        }


        $employee->DivisionCode = $validatedData['DivisionCode'];

        if ($employee->DivisionCode === '20') {
            $classID = 3;
        } else {
            $classID = $validatedData['ClassID'];
        }


        $divisionCode = $validatedData['DivisionCode'];
        // $classID = $validatedData['ClassID'];
        $departmentCode = $validatedData['DepartmentCode'];

        if ($validatedData['Location'] === 'URC3') {

            $bossCode = "500194";
            $employee->BossCode = $bossCode;
        } else {

            $results = DB::table('URC_Division')
                ->join('URC_Employee', 'URC_Division.DivisionCode', '=', 'URC_Employee.DivisionCode')
                ->join('URC_EmployeeClass', 'URC_Employee.ClassID', '=', 'URC_EmployeeClass.ClassID')
                ->leftJoin('URC_Department', 'URC_Employee.DepartmentCode', '=', 'URC_Department.DepartmentCode')
                ->where(function ($query) use ($divisionCode) {
                    $query->where('URC_Division.DivisionCode', $divisionCode)
                        ->orWhere('URC_Division.DivisionCode', '10');
                })
                ->where('URC_EmployeeClass.ClassID', '<', $classID)
                ->where('URC_EmployeeClass.ClassID', '<>', 4)
                ->where(function ($query) use ($departmentCode) {
                    $query->whereNull('URC_Employee.DepartmentCode')
                        ->orWhere('URC_Employee.DepartmentCode', $departmentCode)
                        ->orWhere('URC_Employee.DepartmentCode', '');
                })
                ->orderBy('URC_EmployeeClass.ClassID', 'desc')
                ->select('URC_EmployeeClass.ClassID', 'URC_EmployeeClass.ClassNameTh', 'URC_Employee.EmployeeCode')
                ->get();

            if (!empty($results) && isset($results[0]->EmployeeCode)) {
                $bossCode = $results[0]->EmployeeCode;
                $employee->BossCode = $bossCode;
            }
        }

		if ($divisionCode === '18') {
            $employee->BossCode = "000032";
        } 

        $employee->updated_at = (new \DateTime())->setTime(0, 0)->format('Y-m-d H:i:s');
        $status = $validatedData['EmployeeStatus'];

        $employee->update();

        return redirect()->route('sendLine', ['EmployeeCode' => $EmployeeCode, 'status' => $status])
            ->with('employee2' , $employee2)
            ->with('success', 'Employee updated successfully');
    }


    public function getDepartments(Request $request)
    {
        $divisionCode = $request->input('divisionCode');

        // ค้นหาแผนกที่มี DivisionCode ตรงกับที่ส่งมา
        $departments = URC_Department::where('DivisionCode', $divisionCode)->get();


        // ส่งข้อมูลแผนกกลับเป็น JSON
        return response()->json($departments);
    }
}
