var ResourceMainView = function (){
    var resourceGroupTree;
    var resourceTable;
    var initResourceGroupTree = function (){
        resourceGroupTree = $('#resourceGroupTree').jstree({
            'plugins':['wholerow','types'],
            'core':{
                'themes':{
                    'responsive':false
                },
                "check_callback" : true,
                'data':{
                    'url':'/restapis/security/maintaining/resource/group/list'
                }
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                }
            }
        });
        resourceGroupTree.on('changed.jstree',function(e, data){
            if (data.node == null) return;
            resourceTable.ajax.url('/restapis/security/maintaining/resource/bygroupsid/'+ data.node.id).load();

        });

    }

    var initResourceTable = function(){
        resourceTable = $('#resourceTable').DataTable({
            ajax:{
                type : "POST",
                data : function(data) {
                    return JSON.stringify(data);
                },
                dataType : "json",
                processData : false,
                contentType : 'application/json;charset=UTF-8',
                url:'/restapis/security/maintaining/resource/bygroupsid/1'
            },
            columns : [ {
                data : "name"
            }, {
                data : "location"
            }, {
                data : "description"
            }]
        });

    }

    var reloadResourceGroupTree = function(){
        $('#resourceGroupTree').jstree('refresh');
    }

    return {
        init:function () {
            initResourceGroupTree();
            initResourceTable();

        },

        reloadResourceGroupTree:function(){
            reloadResourceGroupTree();
        }
    }
}();

function addResourceGroup(){
    var formObj = $('#form_add_resource_group').serializeJSON();
    $.ajax({
        type:'POST',
        url:'/restapis/security/maintaining/resource/group',
        contentType:'application/json;charset=UTF-8',
        data:formObj,
        success:function (result, status) {
            ResourceMainView.reloadResourceGroupTree();
            $("form#form_add_resource_group")[0].reset();
            $('#modal_resources').modal('hide');
            $('.alert-danger', $('#form_add_resource_group')).hide();
        },
        error:function(status, error){
            $('.alert-danger', $("#form_add_resource_group")).html('<button class="close" data-close="alert"></button><strong>警告！</strong> ' + status.responseJSON.message);
            $('.alert-danger', $('#form_add_resource_group')).show();
        }
    });
}

if (App.isAngularJsApp() === false) {
    $(document).ready(function() {
        ResourceMainView.init();
    });
}