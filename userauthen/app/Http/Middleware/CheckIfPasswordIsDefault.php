<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Session;

class CheckIfPasswordIsDefault
{
    /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle(Request $request, Closure $next)
    {
        $user = Auth::user();

        if ($user->must_change_password == 0 ) {

            
            return redirect()->route('passwordchange');
        }

        // if ($user && Hash::check('123456', $user->password)) {

            
        //     return redirect()->route('passwordchange');
        // }

        return $next($request);
    }
}
