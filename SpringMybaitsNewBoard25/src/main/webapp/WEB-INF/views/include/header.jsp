<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>AdminLTE 2 | Dashboard</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Ionicons -->
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <link href="/resources/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

  </head>
      <!-- jQuery 2.1.4 -->
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  
  <body class="skin-blue sidebar-mini">
    <div class="wrapper">
      
      <header class="main-header">
        <!-- Logo -->
        <a href="/index.jsp" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>A</b>LT</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>Zerock</b> PROJECT</span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- Messages: style can be found in dropdown.less-->
              <li class="dropdown messages-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <i class="fa fa-envelope-o"></i>
                  <span class="label label-success">5</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="header">You have 5 messages</li>
                  <li>
                    <!-- inner menu: contains the actual data -->
                    <ul class="menu">
                      <li><!-- start message -->
                        <a href="#">
                          <div class="pull-left">
                            <img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="User Image"/>
                          </div>
                          <h4>
                            냥1
                            <small><i class="fa fa-clock-o"></i> 5 mins</small>
                          </h4>
                          <p>냥?</p>
                        </a>
                      </li><!-- end message -->
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="user image"/>
                          </div>
                          <h4>
                            냥2
                            <small><i class="fa fa-clock-o"></i> 2 hours</small>
                          </h4>
                          <p>냥냥?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="user image"/>
                          </div>
                          <h4>
                           냥3
                            <small><i class="fa fa-clock-o"></i> Today</small>
                          </h4>
                          <p>냥냥냥?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="user image"/>
                          </div>
                          <h4>
                            냥4
                            <small><i class="fa fa-clock-o"></i> Yesterday</small>
                          </h4>
                          <p>냥냥냥냥?</p>
                        </a>
                      </li>
                      <li>
                        <a href="#">
                          <div class="pull-left">
                            <img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="user image"/>
                          </div>
                          <h4>
                            냥5
                            <small><i class="fa fa-clock-o"></i> 2 days</small>
                          </h4>
                          <p>냥냥냥냥냥?</p>
                        </a>
                      </li>
                    </ul>
                  </li>
                  <li class="footer"><a href="#">See All Messages</a></li>
                </ul>
              </li>
            

 
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="/resources/dist/img/cat-1.jpg" class="user-image" alt="User Image"/>
                  <span class="hidden-xs">고양이</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- User image -->
                  <li class="user-header">
                    <img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="User Image" />
                    <p>
                      고양이 - 2개월
                      <small>먹을것을 달라냥</small>
                    </p>
                  </li>
                  <!-- Menu Body -->
                  <li class="user-body">
                    <div class="col-xs-4 text-center">
                      <a href="#">밥</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">츄르</a>
                    </div>
                    <div class="col-xs-4 text-center">
                      <a href="#">장난감</a>
                    </div>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="#" class="btn btn-default btn-flat">Profile</a>
                    </div>
                    <div class="pull-right">
                      <a href="#" class="btn btn-default btn-flat">Sign out</a>
                    </div>
                  </li>
                </ul>
              </li>
              <!-- Control Sidebar Toggle Button -->
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- Sidebar user panel -->
          <div class="user-panel">
            <div class="pull-left image">
              <img src="/resources/dist/img/cat-1.jpg" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
              <p>고양이</p>

              <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
          </div>
          <!-- search form -->
          <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
              <input type="text" name="q" class="form-control" placeholder="Search..."/>
              <span class="input-group-btn">
                <button type='submit' name='search' id='search-btn' class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
           
          </form>
         
          <!-- /.search form -->
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
          	<li><a href="<%=request.getContextPath() %>/MemberLoginCheckController"><i class="fa fa-envelope-o"></i>로그인</a></li>
            <li><a href="<%=request.getContextPath() %>/MemberJoinController"><i class="fa fa-user"></i>회원 가입</a></li>
            <li><a href="<%=request.getContextPath() %>/MemberModifyController"><i class="fa fa-users"></i>회원정보수정</a></li>
            <li class="header">MAIN NAVIGATION</li>
            <li class="treeview">
            
              <a href="#">
                <i class="fa fa-dashboard"></i> <span>목록</span> <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li><a href="<%=request.getContextPath() %>/MemberSelectController"><i class="fa fa-users"></i>회원 목록</a></li>
                <li><a href="<%=request.getContextPath() %>/BoardSelectController"><i class="fa fa-pencil-square-o"></i>게시판 목록</a></li>
              </ul>
            </li>
            
           
           
            
         
           
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            로그인&게시판 홈페이지다
            <small>냥냥</small>
          </h1>
         
        </section>