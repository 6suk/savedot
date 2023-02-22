<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<link rel="stylesheet" href="../css/loginForm.css">
</head>
<body>
    <div class="container">
        <!-- Heading -->
        <h1>Save dot</h1>
        
        <!-- Links -->
        <ul class="links">
          <li>
            <a href="#" id="signin">로그인</a>
          </li>
          <li>
            <a href="/user/join" id="signup">회원가입</a>
          </li>
          <li>
            <a href="/user/login" id="reset">RESET</a>
          </li>
        </ul>
        <!-- 일반 로그인 -->
        <form action="/user/login" method="post">
            <!-- id input -->
            <div class="first-input input__block first-input__block">
                <input type="text" name="id" placeholder="아이디" class="input" id="id"/>
            </div>
            <!-- password input -->
            <div class="input__block">
                <input type="password" name="pwd" placeholder="비밀번호" class="input" id="password"/>
            </div>
            <!-- repeat password input -->
            <div class="input__block">
                <input type="password" placeholder="Repeat password" class="input repeat__password" id="repeat__password"    />
            </div>

            <!-- sign in button -->
            <button type="submit" class="signin__btn">
                로그인
            </button>
        </form>
        <!-- separator -->
        <div class="separator">
          <p>OR</p>
        </div>
        <!-- 카카오 로그인 -->
        <button class="kakao__btn">
          <i class="fa fa-kakao"></i>
          카카오계정으로 로그인
        </button>
      </div>
      
      <footer>
        <p>
          <i class="fa fa-heart"></i> 
          <i class="fa fa-heart"></i> 
          <i class="fa fa-heart"></i> 
        </p>
      </footer>
      <script src="../js/loginForm.js?ver=1"></script>
</body>
</html>