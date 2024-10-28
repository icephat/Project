<?php

namespace App\Providers;

use Illuminate\Support\ServiceProvider;
use Illuminate\Support\Facades\Validator;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        //
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        // Validator::extend('telephone_internal', function ($attribute, $value, $parameters, $validator) {
        //     // Custom validation logic
        //     return preg_match('/^[0-9]{3,4}$/', $value); // ตัวอย่าง: ตรวจสอบว่าเป็นตัวเลข 3 ถึง 4 หลัก
        // });

        // Validator::replacer('telephone_internal', function ($message, $attribute, $rule, $parameters) {
        //     return str_replace(':attribute', $attribute, 'The :attribute must be a valid internal telephone number.');
        // });
    }
}
