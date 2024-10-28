<!DOCTYPE html>
<html class="h-100" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>change password</title>

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
</head>

<body class="h-100">

    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="loader">
            <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3"
                    stroke-miterlimit="10" />
            </svg>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->

    <div class="login-form-bg h-100">
        <div class="container h-100">
            <div class="row justify-content-center h-100">
                <div class="col-xl-6">
                    <div class="form-input-content">
                        <div class="card login-form mb-0">
                            <div class="card-body pt-5">
                                <a class="text-center" href="#">
                                    <h4>Change Password</h4>
                                </a>
                                <form class="mt-5 mb-5 login-input" method="POST"
                                    action="{{ route('passwordUpdate') }}">
                                    @csrf
                                    <!-- ส่วนของอีเมล -->
                                    <div>
                                        <x-input-label for="password" :value="__('New Password')" />
                                        <x-text-input type="password" class="form-control"  name="password" id="password" required />
                                    </div>

                                    <!-- ส่วนของรหัสผ่าน -->
                                    <div class="mt-4">
                                        <x-input-label for="password_confirmation" :value="__('Confirm New Password')" />
                                        <x-text-input type="password"  name="password_confirmation"
                                            id="password_confirmation" class="form-control"  required />

                                        <!-- ส่วนของลืมรหัสผ่าน -->
                                        <div class="flex items-center justify-end mt-4">
                                            <!-- ปุ่มเข้าสู่ระบบ -->
                                            <x-primary-button class="ms-3" style="background-color: dodgerblue">
                                                {{ __('Change Password') }}
                                            </x-primary-button>
                                        </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>





    <!-- jQuery -->
    <script src="{{ asset('theme/plugins/jquery/jquery.min.js') }}"></script>

    <!-- Common JavaScript -->
    <script src="{{ asset('theme/plugins/common/common.min.js') }}"></script>
    <!-- Custom JavaScript -->
    <script src="{{ asset('theme/plugins/js/custom.min.js') }}"></script>

    <script src="{{ asset('theme/plugins/js/settings.js') }}"></script>

</body>

</html>
