<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 29/09/2016
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <a href="/data/dashboard/show">首页</a>
            <i class="fa fa-circle"></i>
        </li>
        <li>
            <span>公共密钥管理</span>
        </li>
    </ul>
    <div class="page-toolbar">
        <div id="dashboard-report-range" class="pull-right tooltips btn btn-sm" data-container="body"
             data-placement="bottom" data-original-title="Change dashboard date range">
            <i class="icon-calendar"></i>&nbsp;
            <span class="thin uppercase hidden-xs"></span>&nbsp;
            <i class="fa fa-angle-down"></i>
        </div>
    </div>
</div>
<!-- END PAGE BAR -->
<!-- BEGIN PAGE TITLE-->
<h3 class="page-title"> 公共密钥管理
    <small>Public Key Infrastructure</small>
</h3>
<div class="portlet light bordered">
    <div class="portlet-body">
        <ul class="nav nav-tabs">
            <li class="active">
                <a href="#tab_keypair" data-toggle="tab"> 密钥对 </a>
            </li>
            <li>
                <a href="#tab_6_2" data-toggle="tab"> Cert Signing Req </a>
            </li>
            <li>
                <a href="#tab_6_3" data-toggle="tab"> Certificates </a>
            </li>
            <li>
                <a href="#tab_6_4" data-toggle="tab"> More </a>
            </li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane active in" id="tab_keypair">
                <div class="table-toolbar">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="btn-group">
                                <button id="keypair_new" class="btn green" data-toggle="modal"
                                        href="#modal_adding_keypair">生成密钥对
                                    <i class="fa fa-plus"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="btn-group pull-right">
                                <button class="btn green btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                    <i class="fa fa-angle-down"></i>
                                </button>
                                <ul class="dropdown-menu pull-right">
                                    <li>
                                        <a href="javascript:;"> Print </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;"> Save as PDF </a>
                                    </li>
                                    <li>
                                        <a href="javascript:;"> Export to Excel </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover table-bordered table-hover table-checkable order-column"
                       id="keypairs_table">
                    <thead>
                    <tr>
                        <th> 内部名称</th>
                        <th> 密钥类型</th>
                        <th> 密钥长度</th>
                        <th> 使用计数</th>
                        <th> 密码</th>
                        <th> 查看</th>
                        <th> 删除</th>
                        <th> 导出</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane fade" id="tab_6_2">
                <div class="portlet-body">
                    <div class="table-toolbar">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="btn-group">
                                    <button id="sample_editable_1_new" class="btn green"> Add New
                                        <i class="fa fa-plus"></i>
                                    </button>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="btn-group pull-right">
                                    <button class="btn green btn-outline dropdown-toggle" data-toggle="dropdown">Tools
                                        <i class="fa fa-angle-down"></i>
                                    </button>
                                    <ul class="dropdown-menu pull-right">
                                        <li>
                                            <a href="javascript:;"> Print </a>
                                        </li>
                                        <li>
                                            <a href="javascript:;"> Save as PDF </a>
                                        </li>
                                        <li>
                                            <a href="javascript:;"> Export to Excel </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tab-pane fade" id="tab_6_3">
                <p> Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo
                    retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft
                    beer, iphone
                    skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings
                    gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork
                    biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them,
                    vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr. </p>
            </div>
            <div class="tab-pane fade" id="tab_6_4">
                <p> Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master
                    cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party
                    locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before
                    they sold out farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral, mustache readymade
                    thundercats keffiyeh craft beer marfa ethical. Wolf salvia freegan, sartorial keffiyeh echo park
                    vegan. </p>
            </div>
        </div>
    </div>
</div>

<!-- BEGIN Modal for adding key pair -->
<div id="modal_adding_keypair" class="modal fade" tabindex="-1" data-width="700">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
        <h4 class="modal-title">生成密钥对</h4>
    </div>
    <div class="modal-body">
        <form id="addKeyPairForm" data-ajax="true" method="post" class="form-horizontal">
            <div class="alert alert-danger display-hide">

            </div>
            <fieldset>
                <div class="form-group">
                    <label class="col-sm-3 control-label">内部名称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" name="name"
                               value="${keypPair.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密钥类型</label>
                    <div class="col-sm-4">
                        <select class="form-control" name="type" value="${keyPair.type}">
                            <option value="RSA">RSA</option>
                            <option value="DSA">DSA</option>
                            <option value="EC">EC</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-3 control-label">密钥长度</label>
                    <div class="col-sm-4">
                        <select class="form-control" name="size" value="${keyPair.size}">
                            <option value="1024">1024</option>
                            <option value="2048">2048</option>
                            <option value="4096">4096</option>
                        </select>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <div class="modal-footer">
        <button type="button" data-dismiss="modal" class="btn btn-outline dark">关闭</button>
        <button type="button" class="btn green" onclick="addKeyPair();">生成</button>
    </div>
</div>
<!-- END Modal for adding key pair-->
<div id="modal_view_keypair_info" class="modal fade" tabindex="-1" data-width="700">
    <div class="modal-content">
        <div class="modal-body">
            <img src="/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
            <span> &nbsp;&nbsp;Loading... </span>
        </div>
    </div>
</div>

<div id="modal_delete_keypair_confirm" class="modal fade" tabindex="-1">
    <div class="modal-content">
        <div class="modal-body">
            <img src="/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
            <span> &nbsp;&nbsp;Loading... </span>
        </div>
    </div>
</div>

<div id="modal_export_key" class="modal fade" tabindex="-1">
    <div class="modal-content">
        <div class="modal-body">
            <img src="/assets/global/img/loading-spinner-grey.gif" alt="" class="loading">
            <span> &nbsp;&nbsp;Loading... </span>
        </div>
    </div>
</div>

<script src="/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="/assets/global/scripts/app.min.js" type="text/javascript"></script>
<script src="/assets/pages/scripts/keymanager/keymanager.js" type="text/javascript"></script>




