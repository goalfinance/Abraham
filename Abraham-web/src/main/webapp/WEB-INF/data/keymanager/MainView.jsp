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
        <div id="dashboard-report-range" class="pull-right tooltips btn btn-sm" data-container="body" data-placement="bottom" data-original-title="Change dashboard date range">
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
                <a href="#tab_private_key" data-toggle="tab"> Private Keys </a>
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
                    <div class="tab-pane active in" id="tab_private_key">
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
                        <table class="table table-striped table-hover table-bordered" id="private_keys_table">
                            <thead>
                            <tr>
                                <th> 内部名称 </th>
                                <th> 密钥类型 </th>
                                <th> 密钥长度 </th>
                                <th> use </th>
                                <th> password </th>
                                <th> Delete </th>
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
                        <p> Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade. Messenger bag gentrify pitchfork tattooed craft beer, iphone
                            skateboard locavore carles etsy salvia banksy hoodie helvetica. DIY synth PBR banksy irony. Leggings gentrify squid 8-bit cred pitchfork. Williamsburg banh mi whatever gluten-free, carles pitchfork
                            biodiesel fixie etsy retro mlkshk vice blog. Scenester cred you probably haven't heard of them, vinyl craft beer blog stumptown. Pitchfork sustainable tofu synth chambray yr. </p>
                    </div>
                    <div class="tab-pane fade" id="tab_6_4">
                        <p> Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party
                            locavore wolf cliche high life echo park Austin. Cred vinyl keffiyeh DIY salvia PBR, banh mi before they sold out farm-to-table VHS viral locavore cosby sweater. Lomo wolf viral, mustache readymade
                            thundercats keffiyeh craft beer marfa ethical. Wolf salvia freegan, sartorial keffiyeh echo park vegan. </p>
                    </div>
                </div>
        </div>
    </div>

</div>
<script src="/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="/assets/global/scripts/app.min.js" type="text/javascript"></script>
<script src="/assets/pages/scripts/keymanager/keymanager-datatables.js" type="text/javascript"></script>



