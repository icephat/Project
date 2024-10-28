<!DOCTYPE php>

<php lang="en">

    <head>

        <!-- Basic Page Needs
  ================================================== -->
        <meta charset="utf-8">
        <title>I+</title>

        <!-- Mobile Specific Metas
  ================================================== -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Corporate php5 Template">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
        <meta name="author" content="Themefisher">
        <meta name="generator" content="Themefisher Rappo php Template v1.0">

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="{{ asset('theme/css/style.css') }}">
        <link rel="icon" href="{{ asset('favicon.ico') }}" type="image/x-icon">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/bootstrap/bootstrap.min.css') }}">
        <link rel="stylesheet" href="{{ asset('theme/plugins/bootstrap/bootstrap-select.min.css') }}">

        <!-- Icon Font CSS -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/fontawesome/css/all.css') }}">

        <!-- Themify Icon CSS -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/themify/css/themify-icons.css') }}">


        <style>
            .section-bottom {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                /* ครอบคลุมความสูงของหน้าจอทั้งหมด */
            }

            .selectpicker {
                height: auto !important;
                line-height: normal !important;
                align-items: center;
            }

            .bootstrap-select .dropdown-toggle {
                height: 50px !important;
                display: flex;
                align-items: center;
                /* อาจจะใช้ center หรือ baseline ตามที่เหมาะสม */
                justify-content: flex-end;
                /* ทำให้ข้อความและลูกศรชิดขวา */
            }

            .col-lg-4 {
                text-align: right;
            }
        </style>


    </head>

    <body id="top-header">


        @if (session('success'))
            <div class="alert alert-success">
                {{ session('success') }}
            </div>
        @endif

        @include('layout.header')

        @if ($employee->EmployeeStatus == 'ปกติ')
            <section class="section" id="section-testimonial" style="margin-top: 20px;">

                <div class="container-fluid">
                    <div class="row justify-content-center">
                        <div class="col-lg-10">
                            <div class="card">
                                <div class="card-body">
                                    <div class="form-validation">
                                        <form class="form-valide"
                                            action="{{ route('update', ['EmployeeCode' => $employee->EmployeeCode]) }}"
                                            method="POST" novalidate="novalidate">
                                            @csrf
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label text-right"
                                                    for="EmployeeCode">รหัสพนักงาน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="EmployeeCode"
                                                        name="EmployeeCode" value="{{ $employee->EmployeeCode }}"
                                                        placeholder="กรุณาระบุรหัสพนักงาน 6 หลัก"
                                                        autocomplete="new-password">

                                                    @error('EmployeeCode')
                                                        <span class="text-danger">กรุณาระบุรหัสพนักงาน 6 หลัก </span>
                                                    @enderror

                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="FirstName">ชื่อ (ไทย)
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="FirstName"
                                                        name="FirstName" value="{{ $employee->FirstName }}"
                                                        placeholder="กรุณาระบุชื่อ (ไทย)" autocomplete="new-password">

                                                    @error('FirstName')
                                                        <span class="text-danger">กรุณาระบุชื่อ (ไทย)</span>
                                                    @enderror

                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="LastName">นามสกุล (ไทย)
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="LastName"
                                                        name="LastName" value="{{ $employee->LastName }}"
                                                        placeholder="กรุณาระบุนามสกุล (ไทย)"
                                                        autocomplete="new-password">

                                                    @error('LastName')
                                                        <span class="text-danger">กรุณาระบุชื่อ (ไทย)</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="FirstNameEN">FIRST NAME
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="FirstNameEN"
                                                        name="FirstNameEN" value="{{ $employee->FirstNameEN }}"
                                                        placeholder="กรุณาระบุชื่อ (อังกฤษ)"
                                                        autocomplete="new-password">


                                                    @error('FirstNameEN')
                                                        <span class="text-danger">กรุณาระบุชื่อ (อังกฤษ)</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="LastNameEN">LAST NAME
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="LastNameEN"
                                                        name="LastNameEN" value="{{ $employee->LastNameEN }}"
                                                        placeholder="กรุณาระบุนามสกุล (อังกฤษ)"
                                                        autocomplete="new-password">


                                                    @error('LastNameEN')
                                                        <span class="text-danger">กรุณาระบุนามสกุล (อังกฤษ)</span>
                                                    @enderror
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="NickName">ชื่อเล่น
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="NickName"
                                                        name="NickName" value="{{ $employee->NickName }}"
                                                        placeholder="กรุณาระบุชื่อเล่น" autocomplete="new-password">


                                                    @error('NickName')
                                                        <span class="text-danger">กรุณาระบุชื่อเล่น</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="position">ตำแหน่งงาน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="position"
                                                        name="position" value="{{ $employee->position }}"
                                                        placeholder="กรุณาระบุตำแหน่งงาน" autocomplete="new-password">

                                                    @error('position')
                                                        <span class="text-danger">กรุณาระบุตำแหน่งงาน</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label"
                                                    for="TelephoneInternal">เบอร์โทรภายใน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="TelephoneInternal"
                                                        name="TelephoneInternal"
                                                        value="{{ $employee->TelephoneInternal }}"
                                                        placeholder="กรุณาระบุเบอร์ภายใน 3 หลัก"
                                                        autocomplete="new-password">

                                                    @error('TelephoneInternal')
                                                        <span class="text-danger">กรุณาระบุเบอร์ภายใน 3 หลัก</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label"
                                                    for="MobilePhone">เบอร์โทรส่วนตัว
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="MobilePhone"
                                                        name="MobilePhone" value="{{ $employee->MobilePhone }}"
                                                        placeholder="กรุณาระบุเบอร์โทร 10 หลัก"
                                                        autocomplete="new-password">


                                                    @error('MobilePhone')
                                                        <span class="text-danger">กรุณาระบุเบอร์โทร 10 หลัก</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="Email">Email ภายใน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="Email"
                                                        name="Email" value="{{ $employee->Email }}"
                                                        placeholder="กรุณาระบุ Email" autocomplete="new-password">

                                                    @error('Email')
                                                        <span class="text-danger">กรุณาระบุ Email </span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="FreeEmail">Email ส่วนตัว
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="FreeEmail"
                                                        name="FreeEmail" value="{{ $employee->FreeEmail }}"
                                                        placeholder="กรุณาระบุ Email ส่วนตัว"
                                                        autocomplete="new-password">

                                                    @error('FreeEmail')
                                                        <span class="text-danger">กรุณาระบุ Email ส่วนตัว</span>
                                                    @enderror
                                                </div>
                                            </div>



                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="Location">Location
                                                </label>
                                                <div class="col-lg-2">
                                                    <select class="form-control" id="Location" name="Location">
                                                        <option value="">กรุณาระบุสถานที่</option>
                                                        <option value="URC1"
                                                            {{ $employee->Location == 'URC1' ? 'selected' : '' }}>URC1
                                                        </option>
                                                        <option value="URC3"
                                                            {{ $employee->Location == 'URC3' ? 'selected' : '' }}>URC3
                                                        </option>
                                                        <option value="URCSJ"
                                                            {{ $employee->Location == 'URCSJ' ? 'selected' : '' }}>
                                                            URCSJ
                                                        </option>
                                                    </select>

                                                    @if ($errors->has('Location'))
                                                        <span class="text-danger">กรุณาระบุสถานที่</span>
                                                    @endif
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="DivisionCode">ฝ่าย
                                                </label>
                                                <div class="col-lg-6">

                                                    <select class="selectpicker" data-live-search="true"
                                                        id="DivisionCode" name="DivisionCode">
                                                        <option value="">กรุณาระบุ ฝ่าย</option>
                                                        @foreach ($divisionLists as $divisions)
                                                            <option value="{{ $divisions->DivisionCode }}"
                                                                {{ $divisions->DivisionCode == $employee->DivisionCode ? 'selected' : '' }}>
                                                                {{ $divisions->DivisionNameTh }}</option>
                                                        @endforeach

                                                    </select>

                                                    @error('DivisionCode')
                                                        <span class="text-danger">กรุณาระบุฝ่าย</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="val-range">แผนก
                                                </label>
                                                <div class="col-lg-6">
                                                    <select class="selectpicker" data-live-search="true"
                                                        id="DepartmentCode" name="DepartmentCode">
                                                        <option value="">กรุณาระบุ แผนก</option>
                                                        @foreach ($departmentLists as $department)
                                                            <option value="{{ $department->DepartmentCode }}"
                                                                {{ $department->DepartmentCode == $employee->DepartmentCode ? 'selected' : '' }}>
                                                                {{ $department->DepartmentName }}</option>
                                                        @endforeach

                                                    </select>

                                                    @error('DepartmentCode')
                                                        <span class="text-danger">กรุณาระบุ แผนก</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="val-range">level</label>
                                                <div class="col-lg-6">
                                                    <select class="selectpicker" data-live-search="true"
                                                        id="ClassID" name="ClassID">
                                                        <option value="">กรุณาระบุ level</option>
                                                        @foreach ($classMuti as $class)
                                                            <option value="{{ $class->ClassID }}"
                                                                {{ $class->ClassID == $employee->ClassID ? 'selected' : '' }}>
                                                                {{ $class->ClassNameTh }}
                                                            </option>
                                                        @endforeach
                                                    </select>

                                                    @error('ClassID')
                                                        <span class="text-danger">กรุณาระบุ level</span>
                                                    @enderror
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label"
                                                    for="WorkStartdate">วันที่เริ่มงาน
                                                </label>
                                                <div class="col-lg-2">

                                                    @if ($employee->WorkStartdate != null)
                                                        <input type="date" class="form-control" id="WorkStartdate"
                                                            value="{{ \Carbon\Carbon::parse($employee->WorkStartdate)->format('Y-m-d') }}"
                                                            name="WorkStartdate">
                                                    @else
                                                        <input type="date" class="form-control" id="WorkStartdate"
                                                            name="WorkStartdate">
                                                    @endif
                                                    @if ($errors->has('WorkStartdate'))
                                                        <span class="text-danger">กรุณาระบุ วันที่เริ่มงาน</span>
                                                    @endif
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="EmployeeStatus">สถานะ
                                                </label>
                                                <div class="col-lg-2">
                                                    <select class="form-control" id="EmployeeStatus"
                                                        name="EmployeeStatus">
                                                        <option value="">Please select</option>
                                                        <option value="แก้ไข"
                                                            {{ $employee->EmployeeStatus == 'แก้ไข' ? 'selected' : '' }}>
                                                            แก้ไข</option>
                                                        <option value="ลาออก"
                                                            {{ $employee->EmployeeStatus == 'ลาออก' ? 'selected' : '' }}>
                                                            ลาออก</option>
                                                        <option value="ไม่มาเริ่มงาน"
                                                            {{ $employee->EmployeeStatus == 'ไม่มาเริ่มงาน' ? 'selected' : '' }}>
                                                            ไม่มาเริ่มงาน</option>

                                                    </select>


                                                    @error('EmployeeStatus')
                                                        <span class="text-danger">กรุณาระบุสถานะ</span>
                                                    @enderror
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="LeavDate">วันที่ลาออก
                                                </label>
                                                <div class="col-lg-2">

                                                    <input type="date" class="form-control" id="LeavDate"
                                                        name="LeavDate" disabled>

                                                </div>
                                            </div>




                                            <div class="form-group row ">
                                                <div class="col-lg-8 ml-auto mt-4">
                                                    <a href="{{ url('/') }}" type="button"
                                                        class="btn mb-1 btn-outline-light"
                                                        style="color: black;">ย้อนกลับ</a>
                                                    <button type="submit" class="btn btn-primary">บันทึก</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        @else
            <section class="section" id="section-testimonial" style="margin-top: 20px;">

                <div class="container-fluid">
                    <div class="row justify-content-center">
                        <div class="col-lg-10">
                            <div class="card">
                                <div class="card-body">
                                    <div class="form-validation">
                                        <form class="form-valide"
                                            action="{{ route('update', ['EmployeeCode' => $employee->EmployeeCode]) }}"
                                            method="POST" novalidate="novalidate">
                                            @csrf
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label text-right"
                                                    for="EmployeeCode">รหัสพนักงาน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="EmployeeCode"
                                                        name="EmployeeCode" value="{{ $employee->EmployeeCode }}"
                                                        placeholder="กรุณาระบุรหัสพนักงาน 6 หลัก"
                                                        autocomplete="new-password" disabled>

                                                    @error('EmployeeCode')
                                                        <span class="text-danger">กรุณาระบุรหัสพนักงาน 6 หลัก </span>
                                                    @enderror

                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="FirstName">ชื่อ (ไทย)
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="FirstName"
                                                        name="FirstName" value="{{ $employee->FirstName }}"
                                                        placeholder="กรุณาระบุชื่อ (ไทย)" autocomplete="new-password"
                                                        disabled>

                                                    @error('FirstName')
                                                        <span class="text-danger">กรุณาระบุชื่อ (ไทย)</span>
                                                    @enderror

                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="LastName">นามสกุล (ไทย)
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="LastName"
                                                        name="LastName" value="{{ $employee->LastName }}"
                                                        placeholder="กรุณาระบุนามสกุล (ไทย)"
                                                        autocomplete="new-password" disabled>

                                                    @error('LastName')
                                                        <span class="text-danger">กรุณาระบุชื่อ (ไทย)</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="FirstNameEN">FIRST NAME
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="FirstNameEN"
                                                        name="FirstNameEN" value="{{ $employee->FirstNameEN }}"
                                                        placeholder="กรุณาระบุชื่อ (อังกฤษ)"
                                                        autocomplete="new-password" disabled>


                                                    @error('FirstNameEN')
                                                        <span class="text-danger">กรุณาระบุชื่อ (อังกฤษ)</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="LastNameEN">LAST NAME
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="LastNameEN"
                                                        name="LastNameEN" value="{{ $employee->LastNameEN }}"
                                                        placeholder="กรุณาระบุนามสกุล (อังกฤษ)"
                                                        autocomplete="new-password" disabled>


                                                    @error('LastNameEN')
                                                        <span class="text-danger">กรุณาระบุนามสกุล (อังกฤษ)</span>
                                                    @enderror
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="NickName">ชื่อเล่น
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="NickName"
                                                        name="NickName" value="{{ $employee->NickName }}"
                                                        placeholder="กรุณาระบุชื่อเล่น" autocomplete="new-password"
                                                        disabled>


                                                    @error('NickName')
                                                        <span class="text-danger">กรุณาระบุชื่อเล่น</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="position">ตำแหน่งงาน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="position"
                                                        name="position" value="{{ $employee->position }}"
                                                        placeholder="กรุณาระบุตำแหน่งงาน" autocomplete="new-password"
                                                        disabled>

                                                    @error('position')
                                                        <span class="text-danger">กรุณาระบุตำแหน่งงาน</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label"
                                                    for="TelephoneInternal">เบอร์โทรภายใน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="TelephoneInternal"
                                                        name="TelephoneInternal"
                                                        value="{{ $employee->TelephoneInternal }}"
                                                        placeholder="กรุณาระบุเบอร์ภายใน 3 หลัก"
                                                        autocomplete="new-password" disabled>

                                                    @error('TelephoneInternal')
                                                        <span class="text-danger">กรุณาระบุเบอร์ภายใน 3 หลัก</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label"
                                                    for="MobilePhone">เบอร์โทรส่วนตัว
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="MobilePhone"
                                                        name="MobilePhone" value="{{ $employee->MobilePhone }}"
                                                        placeholder="กรุณาระบุเบอร์โทร 10 หลัก"
                                                        autocomplete="new-password" disabled>


                                                    @error('MobilePhone')
                                                        <span class="text-danger">กรุณาระบุเบอร์โทร 10 หลัก</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="Email">Email ภายใน
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="Email"
                                                        name="Email" value="{{ $employee->Email }}"
                                                        placeholder="กรุณาระบุ Email" autocomplete="new-password"
                                                        disabled>

                                                    @error('Email')
                                                        <span class="text-danger">กรุณาระบุ Email </span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="FreeEmail">Email ส่วนตัว
                                                </label>
                                                <div class="col-lg-6">
                                                    <input type="text" class="form-control" id="FreeEmail"
                                                        name="FreeEmail" value="{{ $employee->FreeEmail }}"
                                                        placeholder="กรุณาระบุ Email ส่วนตัว"
                                                        autocomplete="new-password" disabled>

                                                    @error('FreeEmail')
                                                        <span class="text-danger">กรุณาระบุ Email ส่วนตัว</span>
                                                    @enderror
                                                </div>
                                            </div>



                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="Location">Location
                                                </label>
                                                <div class="col-lg-2">
                                                    <select class="form-control" id="Location" name="Location"
                                                        disabled>
                                                        <option value="">กรุณาระบุสถานที่</option>
                                                        <option value="URC1"
                                                            {{ $employee->Location == 'URC1' ? 'selected' : '' }}>URC1
                                                        </option>
                                                        <option value="URC3"
                                                            {{ $employee->Location == 'URC3' ? 'selected' : '' }}>URC3
                                                        </option>
                                                        <option value="URCSJ"
                                                            {{ $employee->Location == 'URCSJ' ? 'selected' : '' }}>
                                                            URCSJ
                                                        </option>
                                                    </select>

                                                    @if ($errors->has('Location'))
                                                        <span class="text-danger">กรุณาระบุสถานที่</span>
                                                    @endif
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="DivisionCode">ฝ่าย
                                                </label>
                                                <div class="col-lg-6">

                                                    <select class="selectpicker" data-live-search="true"
                                                        id="DivisionCode" name="DivisionCode" disabled>
                                                        <option value="">กรุณาระบุ ฝ่าย</option>
                                                        @foreach ($divisionLists as $divisions)
                                                            <option value="{{ $divisions->DivisionCode }}"
                                                                {{ $divisions->DivisionCode == $employee->DivisionCode ? 'selected' : '' }}>
                                                                {{ $divisions->DivisionNameTh }}</option>
                                                        @endforeach

                                                    </select>

                                                    @error('DivisionCode')
                                                        <span class="text-danger">กรุณาระบุฝ่าย</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="val-range">แผนก
                                                </label>
                                                <div class="col-lg-6">
                                                    <select class="selectpicker" data-live-search="true"
                                                        id="DepartmentCode" name="DepartmentCode" disabled>
                                                        <option value="">กรุณาระบุ แผนก</option>
                                                        @foreach ($departmentLists as $department)
                                                            <option value="{{ $department->DepartmentCode }}"
                                                                {{ $department->DepartmentCode == $employee->DepartmentCode ? 'selected' : '' }}>
                                                                {{ $department->DepartmentName }}</option>
                                                        @endforeach

                                                    </select>

                                                    @error('DepartmentCode')
                                                        <span class="text-danger">กรุณาระบุ แผนก</span>
                                                    @enderror
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="val-range">level</label>
                                                <div class="col-lg-6">
                                                    <select class="selectpicker" data-live-search="true"
                                                        id="ClassID" name="ClassID" disabled>
                                                        <option value="">กรุณาระบุ level</option>
                                                        @foreach ($classMuti as $class)
                                                            <option value="{{ $class->ClassID }}"
                                                                {{ $class->ClassID == $employee->ClassID ? 'selected' : '' }}>
                                                                {{ $class->ClassNameTh }}
                                                            </option>
                                                        @endforeach
                                                    </select>

                                                    @error('ClassID')
                                                        <span class="text-danger">กรุณาระบุ level</span>
                                                    @enderror
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label"
                                                    for="WorkStartdate">วันที่เริ่มงาน
                                                </label>
                                                <div class="col-lg-2">

                                                    @if ($employee->WorkStartdate != null)
                                                        <input type="date" class="form-control" id="WorkStartdate"
                                                            value="{{ \Carbon\Carbon::parse($employee->WorkStartdate)->format('Y-m-d') }}"
                                                            name="WorkStartdate" disabled>
                                                    @else
                                                        <input type="date" class="form-control" id="WorkStartdate"
                                                            name="WorkStartdate" disabled>
                                                    @endif
                                                    @if ($errors->has('WorkStartdate'))
                                                        <span class="text-danger">กรุณาระบุ วันที่เริ่มงาน</span>
                                                    @endif
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label"
                                                    for="EmployeeStatus">สถานะ</label>
                                                <div class="col-lg-2">
                                                    <select class="form-control" id="EmployeeStatus"
                                                        name="EmployeeStatus" disabled>
                                                        <option value="">Please select</option>
                                                        <option value="แก้ไข"
                                                            {{ $employee->EmployeeStatus == 'แก้ไข' ? 'selected' : '' }}>
                                                            แก้ไข</option>
                                                        <option value="ลาออก"
                                                            {{ $employee->EmployeeStatus == 'ลาออก' ? 'selected' : '' }}>
                                                            ลาออก</option>
                                                        <option value="ไม่มาเริ่มงาน"
                                                            {{ $employee->EmployeeStatus == 'ไม่มาเริ่มงาน' ? 'selected' : '' }}>
                                                            ไม่มาเริ่มงาน</option>
                                                    </select>

                                                    @error('EmployeeStatus')
                                                        <span class="text-danger">กรุณาระบุสถานะ</span>
                                                    @enderror
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-lg-4 col-form-label" for="LeavDate">วันที่ลาออก
                                                </label>
                                                <div class="col-lg-2">
                                                    @if ($employee->LeavDate != null)
                                                        <input type="date" class="form-control" 
                                                        name="LeavDate"
                                                        value="{{ \Carbon\Carbon::parse($employee->LeavDate)->format('Y-m-d') }}" disabled/>
                                                    @else
                                                        <input type="date" class="form-control" 
                                                        name="LeavDate" disabled/>
                                                    @endif
                                                    

                                                </div>
                                            </div>


                                            <div class="form-group row ">
                                                <div class="col-lg-8 ml-auto mt-4">
                                                    <a href="{{ url('/') }}" type="button"
                                                        class="btn mb-1 btn-outline-light"
                                                        style="color: black;">ย้อนกลับ</a>
                                                    <button type="submit" class="btn btn-primary"
                                                        disabled>บันทึก</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        @endif





        @include('layout.footer')

        <!--
  Essential Scripts
  =====================================-->
        <!-- jQuery -->
        <script src="{{ asset('theme/plugins/jquery/jquery.min.js') }}"></script>

        <!-- Common JavaScript -->
        <script src="{{ asset('theme/plugins/common/common.min.js') }}"></script>
        <!-- Custom JavaScript -->
        <script src="{{ asset('theme/plugins/js/custom.min.js') }}"></script>

        <script src="{{ asset('theme/plugins/js/settings.js') }}"></script>

        <!-- Bootstrap JavaScript -->
        <script src="{{ asset('theme/plugins/bootstrap/js/bootstrap.bundle.min.js') }}"></script>
        <script src="{{ asset('theme/plugins/bootstrap/js/bootstrap-select.min.js') }}"></script>


        <script>
            document.getElementById('EmployeeStatus').addEventListener('change', function() {
                var leaveDateField = document.getElementById('LeavDate');
                if (this.value === 'ลาออก') {
                    leaveDateField.disabled = false;
                } else {
                    leaveDateField.disabled = true;
                }
            });

            // ตรวจสอบค่าเริ่มต้นเมื่อโหลดหน้าเว็บ
            window.addEventListener('load', function() {
                var employeeStatus = document.getElementById('EmployeeStatus').value;
                var leaveDateField = document.getElementById('LeavDate');
                if (employeeStatus === 'ลาออก') {
                    leaveDateField.disabled = false;
                }
            });
        </script>

    </body>
</php>
