<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8" />
<style>   

button {   
       background-color: #4CAF50;   
       width: 100%;  
        color: orange;   
        padding: 15px;   
        margin: 10px 0px;   
        border: none;   
        cursor: pointer;   
         }   
 form {   
        border: 3px solid #f1f1f1;   
    }   
 input[type=text], input[type=password] {   
        width: 100%;   
        margin: 8px 0;  
        padding: 12px 20px;   
        display: inline-block;   
        border: 2px solid green;   
        box-sizing: border-box;   
    }  
 button:hover {   
        opacity: 0.7;   
    }   
  .cancelbtn {   
        width: auto;   
        padding: 10px 18px;  
        margin: 10px 5px;  
    }   
        
     
 .container {   
        padding: 5px;   
        background-color: lightblue;  
    }   
</style>  
</head>
<body>

<div style="width: 200px;height: 200px">
<form action="#" th:action="@{/login}" th:object="${loginBean}" method="post">  
        <div class="container">   
            <label>Username : </label>   
            <input type="text" placeholder="Enter Username" th:field="*{userName}" name="username" required>  
            <label>Password : </label>   
            <input type="password" placeholder="Enter Password" th:field="*{password}" name="password" required>  
            <button type="submit">Login</button>   
        </div>   
    </form> 
    <div style="overflow-inline: auto;">
    <h5 th:text="${status}"></h5>
    </div>
</div>
</body>

</html>