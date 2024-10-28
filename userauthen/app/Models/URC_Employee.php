<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model; 

class URC_Employee extends Model
{
    use HasFactory;


    // ระบุชื่อ table ที่จะเชื่อมต่อ
    protected $table = 'URC_Employee';

    // กำหนด Primary Key
    protected $primaryKey = 'EmployeeCode';

    // ระบุว่า Primary Key ไม่ใช่ auto-increment
    public $incrementing = false;

    // กำหนดประเภทของ primary key เป็น string
    protected $keyType = 'string';

    // ถ้าตารางของคุณไม่มี timestamps (created_at, updated_at)
    public $timestamps = false;

    // เพิ่ม fillable property
    protected $fillable = [
        'EmployeeCode', 'FirstName', 'LastName', 'NickName', 'DepartmentCode', 'FirstNameEN', 'LastNameEN', 'position', 'MobilePhone', 'TelephoneInternal', 'Email', 'BossCode', 'EmployeeStatus', 'FreeEmail', 'Location', 'DivisionCode', 'ClassID','WorkStartdate','created_at','updated_at','LeavDate'

    ];
}
