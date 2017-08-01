/**
 * Created by panqingrong on 28/12/2016.
 */
var MainView = function(){
    var initSecurityUserTable = function(){
        $("#security_user_table").on('xhr.dt', function(e, settings, json, xhr){
            for (var i = 0, len = json.data.length; i < len; i++){
                json.data[i]["view"] = '<a class="view" data-toggle="modal" data-target="#modal_view_keypair_info" href="/data/keymanager/show_keypair_info_modal?sId=' + json.data[i]["sid"] + '"> View </a>';
                json.data[i]["delete"] = '<a class="delete" data-toggle="modal" data-target="#modal_delete_keypair_confirm" href="/data/keymanager/show_keypair_delete_confirm_info_modal?sId=' + json.data[i]["sid"] + '"> Delete </a>';

            }
        });
        $("#security_user_table").dataTable({
            processing: true,
            serverSide : true,

            ajax : {
                type : "POST",
                data : function(data) {
                    return JSON.stringify(data);
                },
                dataType : "json",
                processData : false,
                contentType : 'application/json;charset=UTF-8',
                url : "/restapis/security/maintaining/user/list"
            },
            lengthMenu: [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],

            // Or you can use remote translation file
            //"language": {
            //   url: '//cdn.datatables.net/plug-ins/3cfcc339e89/i18n/Portuguese.json'
            //},

            // set the initial value
            pageLength: 5,

            language : {
                lengthMenu : '<select class="form-control input-xsmall">'
                + '<option value="5">5</option>'
                + '<option value="10">10</option>'
                + '<option value="20">20</option>'
                + '<option value="30">30</option>'
                + '<option value="40">40</option>'
                + '<option value="50">50</option>'
                + '</select>条记录',//左上角的分页大小显示。
                processing : "载入中",//处理页面数据的时候的显示
                paginate : {//分页的样式文本内容。
                    previous : "上一页",
                    next : "下一页",
                    first : "第一页",
                    last : "最后一页"
                },
                zeroRecords : "没有内容",//table tbody内容为空时，tbody的内容。
                //下面三者构成了总体的左下角的内容。
                info : "总共_PAGES_ 页，第_START_ 到第 _END_ ，筛选之后 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
                infoEmpty : "0条记录",//筛选为空时左下角的显示。
                infoFiltered : ""//筛选之后的左下角筛选提示(另一个是分页信息显示，在上面的info中已经设置，所以可以不显示)，
            },
            columnDefs: [{ // set default column settings
                orderable: true,
                targets: [0]
            }, {
                searchable: false,
                targets: [0]
            }],
            columns : [ {
                data : "userId"
            }, {
                data : "fullName"
            }, {
                data : "nickName"
            }, {
                data : "createTime"
            }, {
                data : "updateTime"
            }, {
                data : "pwdChangeTime"
            }, {
                data : "view"
            }, {
                data : "delete"
            }],
        });
    }

    var reloadSecurityUserTable = function(){
        $("#security_user_table").DataTable().ajax.reload();
    }

    return {

        //main function to initiate the module
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }

            initSecurityUserTable();

        },

        reloadSecurityUserTable:function(){
            reloadSecurityUserTable();
        }

    };

}();

function addSecurityUser(){
    var formObj = $("form#form_add_security_user").serializeJSON();

    $.ajax({
        type:"POST",
        url:"/restapis/security/maintaining/user/register",
        contentType:"application/json;charset=UTF-8",
        data:formObj,
        success:function(result, status){
            MainView.reloadSecurityUserTable();
            $("form#form_add_security_user")[0].reset();
            $('#modal_add_security_user').modal('hide');
            $('.alert-danger', $('#form_add_security_user')).hide();
        },
        error:function(status, error){
            $('.alert-danger', $("#form_add_security_user")).html('<button class="close" data-close="alert"></button><strong>警告！</strong> ' + status.responseJSON.message);
            $('.alert-danger', $('#form_add_security_user')).show();
        }
    });
}

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        MainView.init();

    });
}
