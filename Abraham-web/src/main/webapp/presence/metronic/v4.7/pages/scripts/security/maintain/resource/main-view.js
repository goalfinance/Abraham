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
                    'url':'/restapis/security/maintainting/resource/group/list'
                }
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                }
            }
        });
        resourceGroupTree.on('changed.jstree',function(e, data){
            resourceTable.ajax.url('/restapis/security/maintainting/resource/bygroupsid/'+ data.node.id).load();

        });

    }

    function initResourceTable(){
        resourceTable = $('#resourceTable').DataTable({
            ajax:{
                type : "POST",
                data : function(data) {
                    return JSON.stringify(data);
                },
                dataType : "json",
                processData : false,
                contentType : 'application/json;charset=UTF-8',
                url:'/restapis/security/maintainting/resource/bygroupsid/1'
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


    return {
        init:function () {
            initResourceGroupTree();
            initResourceTable();

        }
    }
}();

if (App.isAngularJsApp() === false) {
    $(document).ready(function() {
        ResourceMainView.init();
    });
}