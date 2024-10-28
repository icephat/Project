<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Services\NotificationService;
use App\Models\URC_Employee;
use App\Models\URC_Division;
use App\Models\URC_Department;
use Illuminate\Support\Facades\DB;
use phpDocumentor\Reflection\Types\Null_;
use Carbon\Carbon;
use GuzzleHttp\Client;

class NotificationController extends Controller
{
    //
    public function sendLineNotification($EmployeeCode, $status)
    {

        $employee2 = session('employee2');
        $employee = URC_Employee::where('EmployeeCode', $EmployeeCode)->firstOrFail();
        // ค้นหาข้อมูลแผนกโดยใช้ departmentCode จากพนักงาน
        $department = URC_Department::where('DepartmentCode', $employee->DepartmentCode)->first();
        $division = URC_Division::where('DivisionCode', $employee->DivisionCode)->first();

        $statusName = '';
        if ($status === 'ลาออก') {
            $statusName = 'ลาออก';
        } elseif ($status === 'แก้ไข') {
            $statusName = 'แก้ไข';
        } elseif ($status === 'ปกติ') {
            $statusName = 'สร้าง';
        } elseif ($status === 'ไม่มาเริ่มงาน') {
            $statusName = 'ไม่มาเริ่มงาน';
        }
        $leaveDate = $employee->LeavDate ? Carbon::parse($employee->LeavDate)->format('Y-m-d') : '';
        if ($department != Null) {
            if ($statusName === 'ลาออก') {

                $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: $department->DepartmentName\nฝ่าย: $division->DivisionNameTh\nวันที่ลาออก: $leaveDate\n";
            } else {
                if ($statusName === 'แก้ไข' && $employee2 != Null) {
                    $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\n";
                    foreach ($employee2 as $emp => $value) {
                        $message .= "$emp: $value\n";
                    }
                } else {
                    $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: $department->DepartmentName\nฝ่าย: $division->DivisionNameTh\n";
                }
            }
        } else {
            if ($statusName === 'ลาออก') {

                $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: \nฝ่าย: $division->DivisionNameTh\nวันที่ลาออก: $leaveDate\n";
            } else {
                if ($statusName === 'แก้ไข' && $employee2 != Null) {

                    $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\n";
                    foreach ($employee2 as $emp => $value) {
                        $message .= "$emp: $value\n";
                    }
                } else {
                    $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: \nฝ่าย: $division->DivisionNameTh\n";
                }
            }
        }


        $token = '5PaK1d4buRjLNa3BgzigErXjpR6mGpFZQcBLSzgrMcL'; // ใช้ token ของคุณ


        $response = $this->sendLineNotify($message, $token);

        if ($response->getStatusCode() == 200) {
            return redirect()->route('home')->with('status', 'Notification sent successfully!');
        } else {
            return redirect()->route('home')->with('error', 'Failed to send message.');
        }
    }

    private function sendLineNotify($message, $token)
    {

        
        $client = new Client();

        $url = 'http://192.0.2.169/api/line.php';

        $response = $client->get($url, [
            'query' => [
                'message' => $message,
                'token' => $token
            ]
        ]);

        return $response;
    }

    // public function sendLineNotification($EmployeeCode, $status)
    // {
    //     $employee2 = session('employee2');
    //     $employee = URC_Employee::where('EmployeeCode', $EmployeeCode)->firstOrFail();
    //     // ค้นหาข้อมูลแผนกโดยใช้ departmentCode จากพนักงาน
    //     $department = URC_Department::where('DepartmentCode', $employee->DepartmentCode)->first();
    //     $division = URC_Division::where('DivisionCode', $employee->DivisionCode)->first();

    //     $statusName = '';
    //     if ($status === 'ลาออก') {
    //         $statusName = 'ลาออก';
    //     } elseif ($status === 'แก้ไข') {
    //         $statusName = 'แก้ไข';
    //     } elseif ($status === 'ปกติ') {
    //         $statusName = 'สร้าง';
    //     } elseif ($status === 'ไม่มาเริ่มงาน') {
    //         $statusName = 'ไม่มาเริ่มงาน';
    //     }
    //     $leaveDate = $employee->LeavDate ? Carbon::parse($employee->LeavDate)->format('Y-m-d') : '';
    //     if ($department != Null) {
    //         if ($statusName === 'ลาออก') {

    //             $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: $department->DepartmentName\nฝ่าย: $division->DivisionNameTh\nวันที่ลาออก: $leaveDate\n";

    //         } else {
    //             if($statusName === 'แก้ไข' && $employee2 != Null){
    //                 $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\n";
    //                 foreach($employee2 as $emp => $value){
    //                     $message .= "$emp: $value\n";
    //                 }
    //             }
    //             else{
    //                 $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: $department->DepartmentName\nฝ่าย: $division->DivisionNameTh\n";
    //             }

    //         }
    //     } else {
    //         if ($statusName === 'ลาออก') {

    //             $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: \nฝ่าย: $division->DivisionNameTh\nวันที่ลาออก: $leaveDate\n";
    //         } else {
    //             if($statusName === 'แก้ไข' && $employee2 != Null){

    //                 $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\n";
    //                 foreach($employee2 as $emp => $value){
    //                     $message .= "$emp: $value\n";
    //                 }
    //             }
    //             else{
    //                 $message = "$statusName\nรหัสพนักงาน:$employee->EmployeeCode\nชื่อ (ไทย):$employee->FirstName\nนามสกุล (ไทย):$employee->LastName\nชื่อเล่น:$employee->NickName\nชื่อ (อังกฤษ):$employee->FirstNameEN\nนามสกุล (อังกฤษ):$employee->LastNameEN\nแผนก: \nฝ่าย: $division->DivisionNameTh\n";
    //             }

    //             }
    //     }

    //     $statusName = $this->notificationService->sendNotification($message);

    //     if ($statusName == 200) {
    //         return redirect()->route('home');
    //     } else {
    //         return "Failed to send notification.";
    //     }
    // }
}
