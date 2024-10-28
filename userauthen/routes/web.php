<?php

use App\Http\Controllers\ProfileController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\HomeController;
use App\Http\Controllers\EmployeeController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\NotificationController;
use App\Http\Controllers\PasswordChangeController;

use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Auth;
use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/

// Route::get('/', function () { 
//     return view('welcome');
// });



Route::get('/', [HomeController::class, 'index'])
    ->middleware(['auth', 'verified'])
    ->name('home');


Route::group(['middleware' => ['auth', 'check.password.default']], function () {
    // เส้นทางที่ต้องการตรวจสอบ
    Route::get('/', [HomeController::class, 'index'])->name('home');
});

Route::middleware(['auth'])->group(function () {
    Route::get('/password/change', [PasswordChangeController::class, 'showChangePasswordForm'])->name('passwordchange');
    Route::post('/password/change', [PasswordChangeController::class, 'updatePassword'])->name('passwordUpdate');
});

Route::post('/check-password', 'AuthController@checkPassword')->name('checkPassword');



// Route สำหรับแสดงฟอร์มเพิ่มพนักงานใหม่
Route::get('/employees/create', [EmployeeController::class, 'create'])->name('createEmployee');
Route::get('/getDepartments', [EmployeeController::class, 'getDepartments'])->name('getDepartments');
Route::post('/employees/save', [EmployeeController::class, 'save'])->name('saveEmployee');

Route::middleware('auth')->group(function () {
    Route::get('/profile', [ProfileController::class, 'edit'])->name('profile.edit');
    Route::patch('/profile', [ProfileController::class, 'update'])->name('profile.update');
    Route::delete('/profile', [ProfileController::class, 'destroy'])->name('profile.destroy');
});

// Route::middleware(['auth', 'role:admin'])->group(function () {
//     // setting system moiture
//     Route::get('/employees/detail/{EmployeeCode}', [EmployeeController::class, 'findByEmployeeCodeAdmin'])->name('detailAdmin');

// });

Route::post('/logout', [LoginController::class, 'logout'])->name('logout');

require __DIR__ . '/auth.php';


Route::get('/employees/{EmployeeCode}', [EmployeeController::class, 'findByEmployeeCode'])
    ->name('findByEmployeeCode');

Route::post('/employees/{EmployeeCode}', [EmployeeController::class, 'update'])->name('update');

Route::get('/employees/{EmployeeCode}/detail', [EmployeeController::class, 'findByEmployeeCode'])->name('detail');

Route::get('/sendLine/{EmployeeCode}/{status}', [NotificationController::class, 'sendLineNotification'])->name('sendLine');


// Route::get('/what/{EmployeeCode}/{status}', [NotificationController::class, 'sendLineNotification'])->name('sendLine');
