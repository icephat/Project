<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class URC_Division extends Model
{
    use HasFactory;
    // ระบุชื่อ table ที่จะเชื่อมต่อ
    protected $table = 'URC_Division';

    protected $primaryKey = 'DivisionCode';

}
