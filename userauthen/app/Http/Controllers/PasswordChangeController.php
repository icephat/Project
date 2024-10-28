<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Session;
use App\Models\User;


class PasswordChangeController extends Controller
{
    public function showChangePasswordForm()
    {
        return view('auth.change');
    }

    public function updatePassword(Request $request)
    {
        // Validate the request
        $request->validate([
            'password' => 'required|string|confirmed',
        ]);

        // Get the authenticated user ID
        $userId = Auth::id();

        // Find the user by ID
        $user = User::find($userId);

        if ($user) {
            // Change the user's password
            $user->password = Hash::make($request->password);
            $user->must_change_password = '1';
            // Save the user
            $user->save();

            // Set success message
            Session::flash('success', 'Password changed successfully!');

            return redirect()->route('home');
        }

        Session::flash('error', 'User not authenticated');
        return redirect()->route('login');
    }

    public function checkPassword(Request $request)
    {
        $password = $request->password;

        // รหัสผ่านที่ถูกแฮชในฐานข้อมูล (แบบฉบับจำลอง)
        $hashedPassword = Hash::make('123456');

        // เปรียบเทียบรหัสผ่าน
        if (Hash::check($password, $hashedPassword)) {
            Session::flash('error', 'กรุณาเปลี่ยนรหัสผ่าน');
            return response()->json(['valid' => true, 'change_password' => true]);
        }

        return response()->json(['valid' => false, 'change_password' => false]);
    }
}
