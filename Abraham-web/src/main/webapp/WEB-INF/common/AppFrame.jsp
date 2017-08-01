<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Tendata CRM</title>
    <meta name="description" content="description">
    <meta name="author" content="DevOOPS">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/static/devoops/plugins/bootstrap/bootstrap.css"
          rel="stylesheet">
    <link href="/static/devoops/plugins/jquery-ui/cv/jquery-ui.min.css"
          rel="stylesheet">
    <link
            href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
            rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Righteous'
          rel='stylesheet' type='text/css'>
    <link href="/static/devoops/plugins/fancybox/jquery.fancybox.css"
          rel="stylesheet">
    <link href="/static/devoops/plugins/fullcalendar/fullcalendar.css"
          rel="stylesheet">
    <link href="/static/devoops/plugins/xcharts/xcharts.min.css"
          rel="stylesheet">
    <link href="/static/devoops/plugins/select2/select2.css"
          rel="stylesheet">
    <link href="/static/devoops/css/style.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/static/devoops/plugins/datatables/cv/datatables.min.css"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>
    <script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!--Start Header-->
<div id="screensaver">
    <canvas id="canvas"></canvas>
    <i class="fa fa-lock" id="screen_unlock"></i>
</div>
<div id="modalbox">
    <div class="devoops-modal">
        <div class="devoops-modal-header">
            <div class="modal-header-name">
                show-sidebar <span>Basic table</span>
            </div>
            <div class="box-icons">
                <a class="close-link"> <i class="fa fa-times"></i>
                </a>
            </div>
        </div>
        <div class="devoops-modal-inner"></div>
        <div class="devoops-modal-bottom"></div>
    </div>
</div>

<div class="modal fade" id="appModal" tabindex="-1" role="dialog"
     aria-labelledby="appModallabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

<div class="modal fade" id="logoutConfirmModal" tabindex="-1"
     role="dialog" aria-labelledby="logoutConfirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;
                </button>
                <h4 class="modal-title" id="logoutConfirmModalLabel">提示</h4>
            </div>
            <div class="modal-body">请确认是否注销本次的系统登录？</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" class="btn btn-primary"
                        onClick="confirmLogout()">确认
                </button>
            </div>
        </div>
    </div>
</div>


<header class="navbar">
    <div class="container-fluid expanded-panel">
        <div class="row">
            <div id="logo" class="col-xs-12 col-sm-2">
                <a href="/common/show_frame">TenData CRM</a>
            </div>
            <div id="top-panel" class="col-xs-12 col-sm-10">
                <div class="row">
                    <div class="col-xs-8 col-sm-4">
                        <a href="#" class="show-sidebar"> <i class="fa fa-bars"></i>
                        </a>
                        <div id="search">
                            <input type="text" placeholder="search"/> <i
                                class="fa fa-search"></i>
                        </div>
                    </div>
                    <div class="col-xs-4 col-sm-8 top-panel-right">
                        <ul class="nav navbar-nav pull-right panel-menu">
                            <li class="hidden-xs"><a href="index.html"
                                                     class="modal-link"> <i class="fa fa-bell"></i> <span
                                    class="badge">7</span>
                            </a></li>
                            <li class="hidden-xs"><a class="ajax-link"
                                                     href="/static/devoops/ajax/calendar.html"> <i
                                    class="fa fa-calendar"></i> <span class="badge">7</span>
                            </a></li>
                            <li class="hidden-xs"><a
                                    href="/static/devoops/ajax/page_messages.html"
                                    class="ajax-link"> <i class="fa fa-envelope"></i> <span
                                    class="badge">7</span>
                            </a></li>
                            <shiro:user>
                                <li class="dropdown"><a href="#"
                                                        class="dropdown-toggle account" data-toggle="dropdown">
                                    <div class="avatar">
                                        <img src="/static/devoops/img/avatar.jpg"
                                             class="img-rounded" alt="avatar"/>
                                    </div>
                                    <i class="fa fa-angle-down pull-right"></i>
                                    <div class="user-mini pull-right">
                                        <span class="welcome">欢迎,</span> <span>${nickname}</span>
                                    </div>
                                </a>
                                    <ul class="dropdown-menu">
                                        <li><a
                                                href="/data/security/maintain/user/show_profile?sId=${userSid}"
                                                class="active ajax-link"> <i class="fa fa-user"></i> <span
                                                class="hidden-sm text">用户信息</span>
                                        </a></li>
                                        <li><a href="/static/devoops/ajax/page_messages.html"
                                               class="ajax-link"> <i class="fa fa-envelope"></i> <span
                                                class="hidden-sm text">消息</span>
                                        </a></li>
                                        <li><a href="/static/devoops/ajax/gallery_simple.html"
                                               class="ajax-link"> <i class="fa fa-picture-o"></i> <span
                                                class="hidden-sm text">照片</span>
                                        </a></li>
                                        <li><a href="/static/devoops/ajax/calendar.html"
                                               class="ajax-link"> <i class="fa fa-tasks"></i> <span
                                                class="hidden-sm text">任务</span>
                                        </a></li>
                                        <li><a href="/data/security/maintain/user/main_view"
                                               class="active ajax-link"> <i class="fa fa-cog"></i> <span
                                                class="hidden-sm text">设置</span>
                                        </a></li>
                                        <li><a data-toggle="modal"
                                               data-target="#logoutConfirmModal" href="#"> <i
                                                class="fa fa-power-off"></i> <span class="hidden-sm text">注销</span>
                                        </a></li>
                                    </ul>
                                </li>
                            </shiro:user>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!--End Header-->
<!--Start Container-->
<div id="main" class="container-fluid">
    <div class="row">
        <div id="sidebar-left" class="col-xs-2 col-sm-2">
            <ul class="nav main-menu">
                <c:choose>
                    <c:when test="${groups.size() <= 0}">
                        <li>
                            <a>test</a>
                        </li>
                    </c:when>

                    <c:otherwise>
                        <c:forEach items="${groups}" var="group">
                            <c:choose>
                                <c:when test="${group.resources.size() <= 0}">
                                    <li><a href="/static/devoops/ajax/dashboard.html"
                                           class="active ajax-link"> <i class="fa fa-dashboard"></i>
                                        <span class="hidden-xs">${group.name}</span>
                                    </a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="dropdown"><a href="#" class="dropdown-toggle">
                                        <i class="fa fa-bar-chart-o"></i> <span class="hidden-xs">${group.name}</span>
                                    </a>
                                        <ul class="dropdown-menu">
                                            <c:forEach items="${group.resources}" var="resource">
                                                <li><a class="ajax-link"
                                                       href="${resource.rlocation}">${resource.name}</a></li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <!--Start Content-->
        <div id="content" class="col-xs-12 col-sm-10">
            <div class="preloader">
                <img src="/static/devoops/img/devoops_getdata.gif"
                     class="devoops-getdata" alt="preloader"/>
            </div>
            <div id="ajax-content"></div>
        </div>
        <!--End Content-->
    </div>
</div>
<!--End Container-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!--<script src="http://code.jquery.com/jquery.js"></script>-->
<script src="/static/devoops/plugins/jquery/jquery-3.1.0.js"></script>
<script src="/static/devoops/plugins/jquery-ui/cv/jquery-ui.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/static/devoops/plugins/bootstrap/bootstrap.min.js"></script>
<script src="/static/devoops/js/jquery.serialize-object.min.js"></script>
<script
        src="/static/devoops/plugins/justified-gallery/jquery.justifiedgallery.min.js"></script>
<script src="/static/devoops/plugins/tinymce/tinymce.min.js"></script>
<script src="/static/devoops/plugins/tinymce/jquery.tinymce.min.js"></script>
<!-- All functions for this theme + document.ready processing -->
<script src="/static/devoops/js/devoops.js"></script>
<script src="/static/devoops/js/tendata.js"></script>
<script type="text/javascript" src="/static/devoops/plugins/datatables/cv/datatables.js"></script>
</body>
</html>
