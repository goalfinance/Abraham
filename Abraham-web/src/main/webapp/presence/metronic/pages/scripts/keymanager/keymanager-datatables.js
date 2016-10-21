/**
 * Created by panqingrong on 19/10/2016.
 */
var KMDataTables = function(){
    var initPrivateKeysTable = function(){
        var table = $('#private_keys_table');
        // begin first table
        table.dataTable({
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
                url : "/restapis/keymanager/privatekey/list"
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
                info : "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
                infoEmpty : "0条记录",//筛选为空时左下角的显示。
                infoFiltered : ""//筛选之后的左下角筛选提示(另一个是分页信息显示，在上面的info中已经设置，所以可以不显示)，
            },
            columnDefs: [{ // set default column settings
                'orderable': true,
                'targets': [0]
            }, {
                "searchable": true,
                "targets": [0]
            }],
            columns : [ {
                "data" : "sid"
            }, {
                "data" : "name"
            }, {
                "data" : "type"
            }, {
                "data" : "size"
            }, {
                "data" : "password"
            }],
        });

        //var tableWrapper = $("#private_keys_table_wrapper");

    }

    return {

        //main function to initiate the module
        init: function () {
            if (!jQuery().dataTable) {
                return;
            }

            initPrivateKeysTable();

        }

    };

}();
if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        KMDataTables.init();
    });
}