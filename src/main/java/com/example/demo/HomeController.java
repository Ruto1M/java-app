package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return """
               <!DOCTYPE html>
               <html lang="en">
               <head>
                   <meta charset="UTF-8">
                   <meta name="viewport" content="width=device-width, initial-scale=1.0">
                   <title>App Status</title>
                   <style>
                       body {
                           background-color: #f4f4f4;
                           font-family: Arial, sans-serif;
                           display: flex;
                           justify-content: center;
                           align-items: center;
                           height: 100vh;
                           margin: 0;
                       }
                       .container {
                           text-align: center;
                           background-color: white;
                           padding: 40px 60px;
                           border-radius: 12px;
                           box-shadow: 0 0 15px rgba(0,0,0,0.1);
                       }
                       h1 {
                           color: #2c3e50;
                           font-size: 3rem;
                           margin: 0;
                       }
                       p {
                           margin-top: 10px;
                           color: #555;
                           font-size: 1.2rem;
                       }
                   </style>
               </head>
               <body>
                   <div class="container">
                       <h1>✅ Application is up</h1>
                       <p>Your Java Spring Boot service is running smoothly 🚀</p>
                   </div>
               </body>
               </html>
               """;
    }

    @GetMapping("/healthz")
    public String healthz() {
        return "OK";
    }

    @GetMapping("/ready")
    public String ready() {
        return "READY";
    }
}
