<!DOCTYPE php>

<!--
 // WEBSITE: https://themefisher.com
 // TWITTER: https://twitter.com/themefisher
 // FACEBOOK: https://www.facebook.com/themefisher
 // GITHUB: https://github.com/themefisher/
-->

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

        <!-- theme meta -->
        <meta name="theme-name" content="rappo" />

        <!-- bootstrap -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/bootstrap/bootstrap.min.css') }}">
        <link rel="icon" href="{{ asset('favicon.ico') }}" type="image/x-icon">

        <!-- Animate -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/animate-css/animate.css') }}">
        <!-- Icon Font css -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/fontawesome/css/all.css') }}">
        <link rel="stylesheet" href="{{ asset('theme/plugins/fonts/Pe-icon-7-stroke.css') }}">
        <!-- Themify icon Css -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/themify/css/themify-icons.css') }}">
        <!-- Slick Carousel CSS -->
        <link rel="stylesheet" href="{{ asset('theme/plugins/slick-carousel/slick/slick.css') }}">
        <link rel="stylesheet" href="{{ asset('theme/plugins/slick-carousel/slick/slick-theme.css') }}">

        <!-- Main Stylesheet -->
        <link rel="stylesheet" href="{{ asset('theme/css/style2.css') }}">

        <link href="{{ asset('theme/css/font-awesome.css') }}" rel="stylesheet">

        <!--Favicon-->
        <link rel="icon" href="{{ asset('theme/images/favicon.png') }}" type="image/x-icon">
        <link href="{{ asset('theme/plugins/tables/css/datatable/dataTables.bootstrap4.min.css') }}" rel="stylesheet">

    </head>

    <body id="top-header">



        @include('layout.header')
    

        <section class="banner-area py-5">
            <!-- Content -->
            <div class="col-md-12 col-lg-12 text-center">
                <div class="section-heading">
                    
                    <!-- Subheading -->
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="section px-2 bg-white shadow text-center">

                                    <div class="container-fluid">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="card">

                                                    <div class="card-body">
                                                        <h4 class="card-title">รายชื่อพนักงานทั้งหมด</h4>
                                                        <a href="{{ route('createEmployee') }}" type="button"
                                                            class="btn mb-1 btn-primary float-right">เพิ่มพนักงานใหม่</a>
                                                        <div class="table-responsive">
                                                            <table 
                                                            class="table table-striped table-bordered zero-configuration">
                                                                <thead style="font-size: small;">
                                                                    <tr>
                                                                        <th>รหัสพนักงาน</th>
                                                                        <th>ชื่อ (ไทย)</th>
                                                                        <th>นามสกุล (ไทย)</th>
                                                                        <th>FIRST NAME</th>
                                                                        <th>LAST NAME</th>
                                                                        <th>ตำแหน่งงาน</th>
                                                                        <th>เบอร์ภายใน</th>
                                                                        <th>แผนก</th>
                                                                        <th>สถานะ</th>
                                                                        <th>#</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    @foreach ($employees as $employee)
                                                                        <tr>
                                                                            <td>{{ $employee->EmployeeCode }}</td>
                                                                            <td>{{ $employee->FirstName }}</td>
                                                                            <td>{{ $employee->LastName }}</td>
                                                                            <td>{{ $employee->FirstNameEN }}</td>
                                                                            <td>{{ $employee->LastNameEN }}</td>
                                                                            <td>{{ $employee->position }}</td>
                                                                            <td>{{ $employee->TelephoneInternal }}
                                                                            </td>
                                                                            <td>{{ $employee->DepartmentName }}
                                                                            </td>
                                                                            <td>{{ $employee->EmployeeStatus }}</td>
                                                                            <td>
                                                                                <a href="{{ route('findByEmployeeCode', ['EmployeeCode' => $employee->EmployeeCode]) }}"
                                                                                    type="button"
                                                                                    class="btn mb-1 btn-primary">
                                                                                    <span class="ti-pencil-alt"></span>
                                                                                </a>
                                                                            </td>
                                                                        </tr>
                                                                    @endforeach

                                                                </tbody>

                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        




        @include('layout.footer')
        <script>
            // Check if there is a success message
            let successMessage = "{{ Session::get('success') }}";
            if (successMessage) {
                alert(successMessage); // Show success alert
            }
        </script>
        <!--
  Essential Scripts
  =====================================-->
        <!-- jQuery -->
        <script src="{{ asset('theme/plugins/jquery/jquery.min.js') }}"></script>
        <!-- Bootstrap -->
        <script src="{{ asset('theme/plugins/bootstrap/bootstrap.min.js') }}"></script>
        <!-- Slick Slider -->
        <script src="{{ asset('theme/plugins/slick-carousel/slick/slick.min.js') }}"></script>

        <script src="{{ asset('theme/plugins/google-map/gmap.js') }}"></script>

        <script src="{{ asset('theme/js/script.js') }}"></script>
        <script src="{{ asset('theme/plugins/tables/js/jquery.dataTables.min.js') }}"></script>
        <script src="{{ asset('theme/plugins/tables/js/datatable/dataTables.bootstrap4.min.js') }}"></script>
        <script src="{{ asset('theme/plugins/tables/js/datatable-init/datatable-basic.min.js') }}"></script>

    </body>
</php>
