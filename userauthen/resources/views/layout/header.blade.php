<!-- NAVBAR
    ================================================= -->
<div class="col-lg-12" style="background-color: #7571f9; color:white">
    <header class="d-flex flex-wrap justify-content-between align-items-center py-3 ">
        <div class="col-lg-12">
            <!-- Links -->

            <div class="row">

                <div class="col-lg-6">

                    <a class="nav-link" href="{{ route('home') }}">
                        <h3 style="color: white;">Home</h3>
                    </a>

                </div>
                <div class="col-lg-6 text-right">

                    <div class="row ">
                        <div class="col-sm-10">
                            <form id="logout-form" action="{{ route('logout') }}" method="POST" style="display: none;">
                                @csrf
                            </form>
                            <a href="{{ route('logout') }}" style="text-decoration: none;" onclick="event.preventDefault();
                                var confirmLogout = confirm('คุณต้องการทำการออกจากระบบหรือไม่?');
                                if (confirmLogout) {
                                    document.getElementById('logout-form').submit();
                                }">
                                <h3 style="color: white; margin-right: -5px;" class="ti-power-off text-right mt-2"></h3>
                                <span style="color: white; margin-right: -12px;">logout</span>
                            </a>
                        </div>
                        <div class="col-sm-2 ">
                            <img class="logo-right " src="{{ asset('theme/images/LOGO3.png') }}" width="100" height="50" alt="Logo" />

                        </div>

                        
                        
    

                    </div>
                    
                </div>

                



            </div>

        </div> <!-- / .navbar-collapse -->






    </header>
</div>