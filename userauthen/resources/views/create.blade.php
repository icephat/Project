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

            ::placeholder {
                color: red;
            }
            .col-lg-4{
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

        <section class="section" id="section-testimonial" style="margin-top: 20px;">

            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="col-lg-10">
                        <div class="card">
                            <div class="card-body">
                                <div class="form-validation">
                                    <form class="form-valide" action="{{ route('saveEmployee') }}" method="POST"
                                        novalidate="novalidate" autocomplete="new-password">
                                        @csrf
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="EmployeeCode">รหัสพนักงาน
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="EmployeeCode"
                                                    name="EmployeeCode" placeholder="กรุณาระบุรหัสพนักงาน 6 หลัก" autocomplete="new-password">


                                                @error('EmployeeCode')
                                                    <span class="text-danger">
                                                        @if ($message == 'ข้อมูลพนักงานซ้ำ')
                                                            ข้อมูลพนักงานซ้ำ
                                                        @else
                                                            กรุณาระบุรหัสพนักงาน 6 หลัก
                                                        @endif
                                                    </span>
                                                @enderror
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="FirstName">ชื่อ (ไทย)
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="FirstName"
                                                    name="FirstName" placeholder="กรุณาระบุชื่อ (ไทย)" autocomplete="new-password">

                                                @if ($errors->has('FirstName'))
                                                    <span class="text-danger">กรุณาระบุชื่อ (ไทย)</span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="LastName">นามสกุล (ไทย)
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="LastName"
                                                    name="LastName" placeholder="กรุณาระบุนามสกุล (ไทย)" autocomplete="new-password">

                                                @if ($errors->has('LastName'))
                                                    <span class="text-danger">กรุณาระบุนามสกุล (ไทย)</span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="FirstNameEN">FIRST NAME
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="FirstNameEN"
                                                    name="FirstNameEN" placeholder="กรุณาระบุชื่อ (อังกฤษ)" autocomplete="new-password">

                                                @if ($errors->has('FirstNameEN'))
                                                    <span class="text-danger">กรุณาระบุชื่อ (อังกฤษ)</span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="LastNameEN">LAST NAME
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="LastNameEN"
                                                    name="LastNameEN" placeholder="กรุณาระบุนามสกุล (อังกฤษ)" autocomplete="new-password">


                                                @if ($errors->has('LastNameEN'))
                                                    <span class="text-danger">กรุณาระบุนามสกุล (อังกฤษ)</span>
                                                @endif
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="NickName">ชื่อเล่น
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="NickName"
                                                    name="NickName" placeholder="กรุณาระบุชื่อเล่น" autocomplete="new-password">

                                                @if ($errors->has('NickName'))
                                                    <span class="text-danger">กรุณาระบุชื่อเล่น</span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="position">ตำแหน่งงาน
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="position"
                                                    name="position" placeholder="กรุณาระบุตำแหน่งงาน" autocomplete="new-password">

                                                @if ($errors->has('position'))
                                                    <span class="text-danger">กรุณาระบุตำแหน่งงาน</span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label"
                                                for="TelephoneInternal">เบอร์โทรภายใน</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="TelephoneInternal"
                                                    name="TelephoneInternal" value="{{ old('TelephoneInternal') }}"
                                                    placeholder="กรุณาระบุเบอร์ภายใน 3 หลัก" autocomplete="new-password">

                                                @if ($errors->has('TelephoneInternal'))
                                                    <span class="text-danger">กรุณาระบุเบอร์ภายใน 3 หลัก</span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="MobilePhone">เบอร์โทรส่วนตัว
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="MobilePhone"
                                                    name="MobilePhone" placeholder="กรุณาระบุเบอร์โทร 10 หลัก" autocomplete="new-password">

                                                @if ($errors->has('MobilePhone'))
                                                    <span class="text-danger">กรุณาระบุเบอร์โทร 10 หลัก</span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="Email">Email ภายใน
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="Email"
                                                    name="Email" placeholder="กรุณาระบุ Email" autocomplete="new-password">

                                                @if ($errors->has('Email'))
                                                    <span class="text-danger">กรุณาระบุ Email </span>
                                                @endif
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="FreeEmail">Email ส่วนตัว
                                            </label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" id="FreeEmail"
                                                    name="FreeEmail" placeholder="กรุณาระบุ Email ส่วนตัว" autocomplete="new-password">

                                                @if ($errors->has('FreeEmail'))
                                                    <span class="text-danger">กรุณาระบุ Email ส่วนตัว</span>
                                                @endif
                                            </div>
                                        </div>
                                        

                                        
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="Location">Location
                                            </label>
                                            <div class="col-lg-2">
                                                
                                                <select class="form-control" id="Location"
                                                    name="Location">
                                                    <option value="">กรุณาระบุสถานที่</option>
                                                    <option value="URC1">URC1</option>
                                                    <option value="URC3">URC3</option>
                                                    <option value="URCSJ">URCSJ</option>
                                                </select>

                                                @if ($errors->has('Location'))
                                                    <span class="text-danger">กรุณาระบุสถานที่</span>
                                                @endif
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="DepartmentCode">ฝ่าย
                                            </label>
                                            <div class="col-lg-6">

                                                <select class="selectpicker" data-live-search="true"
                                                    id="DivisionCode" name="DivisionCode">
                                                    <option value="">กรุณาระบุฝ่าย</option>
                                                    @foreach ($divisionLists as $division)
                                                        <option value="{{ $division->DivisionCode }}">
                                                            {{ $division->DivisionNameTh }}</option>
                                                    @endforeach

                                                </select>

                                                @if ($errors->has('DivisionCode'))
                                                    <span class="text-danger">กรุณาระบุฝ่าย</span>
                                                @endif



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
                                                        <option value="{{ $department->DepartmentCode }}">
                                                            {{ $department->DepartmentName }}</option>
                                                    @endforeach

                                                </select>
                                                @if ($errors->has('DepartmentCode'))
                                                    <span class="text-danger">กรุณาระบุ แผนก</span>
                                                @endif


                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="WorkStartdate">วันที่เริ่มงาน
                                            </label>
                                            <div class="col-lg-2">
                                                <input type="date" class="form-control" id="WorkStartdate"
                                                    name="WorkStartdate">

                                                @if ($errors->has('WorkStartdate'))
                                                    <span class="text-danger">กรุณาระบุ วันที่เริ่มงาน</span>
                                                @endif
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="val-range">level</label>
                                            <div class="col-lg-2">
                                                <select class="selectpicker" data-live-search="true" id="ClassID"
                                                    name="ClassID">
                                                    <option value="">กรุณาระบุ level</option>
                                                    @foreach ($classMuti as $class)
                                                        <option value="{{ $class->ClassID }}">
                                                            {{ $class->ClassNameTh }}
                                                        </option>
                                                    @endforeach
                                                </select>
                                                @if ($errors->has('ClassID'))
                                                    <span class="text-danger">กรุณาระบุ level</span>
                                                @endif
                                            </div>
                                        </div>

                                        

                                        <div class="form-group row">
                                            <div class="col-lg-8 ml-auto mt-3">
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

       



    </body>
</php>
