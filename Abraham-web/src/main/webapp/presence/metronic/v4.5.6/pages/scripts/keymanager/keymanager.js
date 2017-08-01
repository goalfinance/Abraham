/**
 * Created by panqingrong on 19/10/2016.
 */
function addKeyPair() {
    var formObj = $("form#addKeyPairForm").serializeJSON();
    $.ajax({
        type: "POST",
        url: "/restapis/keymanager/keypair",
        contentType: 'application/json',
        data: formObj,
        success: function (result, status) {
            var table = $('#keypairs_table').DataTable();
            table.ajax.reload();
            document.getElementById("addKeyPairForm").reset();
            $('#modal_adding_keypair').modal('hide');
            $('.alert-danger', $('#addKeyPairForm')).hide();
        },
        error: function (status, error) {
            $('.alert-danger', $('#addKeyPairForm')).html('<button class="close" data-close="alert"></button><strong>警告！</strong> ' + status.responseJSON.message);
            $('.alert-danger', $('#addKeyPairForm')).show();
        }
    });
}

function deleteKeyPair(sId) {
    $.ajax({
        type: "DELETE",
        url: "/restapis/keymanager/keypair/" + sId,
        success: function (result, status) {
            var table = $('#keypairs_table').DataTable();
            table.ajax.reload();
            $('#modal_delete_keypair_confirm').modal('hide');
        },
        error: function (status, error) {
            $('.alert-danger', $('#keypairDeleteConfirm')).html('<button class="close" data-close="alert"></button><strong>警告！</strong> ' + status.responseJSON.message);
            $('.alert-danger', $('#keypairDeleteConfirm')).show();
        }
    });
}

function exportKey(sId) {
    var formObj = $("form#exportKeyForm").serializeJSON();
    $.ajax({
        type: "POST",
        url: "/restapis/keymanager/keypair/export/" + sId,
        contentType: 'application/json',
        data: formObj,
        success: function (response, status, request) {
            window.open(response);
        },
        error: function (status, error) {

        }

    });
}

var KManager = function () {
    var initKeyPairsTable = function () {
        var table = $('#keypairs_table');
        table.on('xhr.dt', function (e, settings, json, xhr) {
            for (var i = 0, len = json.data.length; i < len; i++) {
                json.data[i]["view"] = '<a class="view" data-toggle="modal" data-target="#modal_view_keypair_info" href="/data/keymanager/show_keypair_info_modal?sId=' + json.data[i]["sid"] + '"> View </a>';
                json.data[i]["delete"] = '<a class="delete" data-toggle="modal" data-target="#modal_delete_keypair_confirm" href="/data/keymanager/show_keypair_delete_confirm_info_modal?sId=' + json.data[i]["sid"] + '"> Delete </a>';
                json.data[i]["export"] = '<a class="export" data-toggle="modal" data-target="#modal_export_key" href="/data/keymanager/show_export_key_info_modal?sId=' + json.data[i]["sid"] + '"> Export </a>';

            }
        });
        // begin first table
        table.dataTable({
            processing: true,
            serverSide: true,

            ajax: {
                type: "POST",
                data: function (data) {
                    return JSON.stringify(data);
                },
                dataType: "json",
                processData: false,
                contentType: 'application/json;charset=UTF-8',
                url: "/restapis/keymanager/keypair/list"
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

            language: {
                lengthMenu: '<select class="form-control input-xsmall">'
                + '<option value="5">5</option>'
                + '<option value="10">10</option>'
                + '<option value="20">20</option>'
                + '<option value="30">30</option>'
                + '<option value="40">40</option>'
                + '<option value="50">50</option>'
                + '</select>条记录',//左上角的分页大小显示。
                processing: "载入中",//处理页面数据的时候的显示
                paginate: {//分页的样式文本内容。
                    previous: "上一页",
                    next: "下一页",
                    first: "第一页",
                    last: "最后一页"
                },
                zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
                //下面三者构成了总体的左下角的内容。
                info: "总共_PAGES_ 页，第_START_ 到第 _END_ ，筛选之后 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
                infoEmpty: "0条记录",//筛选为空时左下角的显示。
                infoFiltered: ""//筛选之后的左下角筛选提示(另一个是分页信息显示，在上面的info中已经设置，所以可以不显示)，
            },
            columnDefs: [{ // set default column settings
                orderable: true,
                targets: [0]
            }, {
                searchable: false,
                targets: [0]
            }],
            columns: [{
                data: "name"
            }, {
                data: "type"
            }, {
                data: "size"
            }, {
                data: "use"
            }, {
                data: "password"
            }, {
                data: "view"
            }, {
                data: "delete"
            }, {
                data: "export"
            }],
        });

    }

    return {

        //main function to initiate the module
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }

            initKeyPairsTable();

        }

    };

}();
if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function () {
        KManager.init();
        // $('#modal_adding_keypair').on('show.bs.modal', function(){
        //     alert("test");
        // });
    });
}