<?php 
// app/Services/LineNotifyService.php
// namespace App\Services;

// use GuzzleHttp\Client;

// class NotificationService
// {
//     protected $client;
//     protected $lineNotifyToken;

//     public function __construct()
//     {
//         $this->client = new Client();
//         $this->lineNotifyToken = '5PaK1d4buRjLNa3BgzigErXjpR6mGpFZQcBLSzgrMcL'; // แทนที่ด้วย Token ของคุณ
//     }

//     public function sendNotification($message)
//     {
//         $response = $this->client->post('https://notify-api.line.me/api/notify', [
//             'headers' => [
//                 'Authorization' => 'Bearer ' . $this->lineNotifyToken,
//             ],
//             'form_params' => [
//                 'message' => $message,
//             ],
//         ]);

//         return ;

        
//     }
// }
