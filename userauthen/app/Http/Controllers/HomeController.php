<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\URC_Employee;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Auth;

class HomeController extends Controller
{
    public function index()
    {
        // ดึงข้อมูลทั้งหมดจากตาราง products
        // $employees = URC_Employee::all();
        $loggedInEmployeeStatus = Auth::user()->role;
        if ($loggedInEmployeeStatus === 'adminURC3') {
            $employees = DB::table('URC_Employee')
                ->leftJoin('URC_Department', 'URC_Employee.DepartmentCode', '=', 'URC_Department.DepartmentCode')
                ->select(
                    'URC_Employee.EmployeeCode',
                    'URC_Employee.FirstName',
                    'URC_Employee.LastName',
                    'URC_Employee.NickName',
                    'URC_Employee.DepartmentCode',
                    'URC_Employee.FirstNameEN',
                    'URC_Employee.LastNameEN',
                    'URC_Employee.position',
                    'URC_Employee.MobilePhone',
                    'URC_Employee.TelephoneInternal',
                    'URC_Employee.Email',
                    'URC_Employee.BossCode',
                    'URC_Employee.EmployeeStatus',
                    'URC_Employee.FreeEmail',
                    'URC_Employee.Location',
                    'URC_Employee.DivisionCode',
                    'URC_Employee.ClassID',
                    'URC_Department.DepartmentName'
                )
                ->where('URC_Employee.Location', '=', 'URC3')
                ->get();
        } else {
            $employees = DB::table('URC_Employee')
                ->leftJoin('URC_Department', 'URC_Employee.DepartmentCode', '=', 'URC_Department.DepartmentCode')
                ->select(
                    'URC_Employee.EmployeeCode',
                    'URC_Employee.FirstName',
                    'URC_Employee.LastName',
                    'URC_Employee.NickName',
                    'URC_Employee.DepartmentCode',
                    'URC_Employee.FirstNameEN',
                    'URC_Employee.LastNameEN',
                    'URC_Employee.position',
                    'URC_Employee.MobilePhone',
                    'URC_Employee.TelephoneInternal',
                    'URC_Employee.Email',
                    'URC_Employee.BossCode',
                    'URC_Employee.EmployeeStatus',
                    'URC_Employee.FreeEmail',
                    'URC_Employee.Location',
                    'URC_Employee.DivisionCode',
                    'URC_Employee.ClassID',
                    'URC_Department.DepartmentName'
                )
                ->get();
        }



        // ส่งข้อมูลไปยัง view
        return view('home', ['employees' => $employees]);
    }
}
