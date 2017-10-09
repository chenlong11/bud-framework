//jquery function
(function () {

    //form -> json
    $.fn.serializeObject = function () {
        var obj = {};
        var ary = this.serializeArray();
        $.each(ary, function () {
            if (obj[this.name]) {
                if (!obj[this.name].push) {
                    obj[this.name] = [obj[this.name]];
                }
                obj[this.name].push(this.value || '');
            } else {
                obj[this.name] = this.value || '';
            }
        });
        return obj;
    };

    //显示table方法
    $.fn.table = function (opts) {
        var tableId = $(this).attr('id');
        //提示信息
        var lang = {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页 _MENU_ 项",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
            "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页",
                "sJump": "跳转"
            },
            "oAria": {
                "sSortAscending": ": 升序",
                "sSortDescending": ": 降序"
            }
        };


        var t = $('#' + tableId).DataTable({
            language: lang, //提示信息
            autoWidth: false, //禁用自动调整列宽
            processing: true, //隐藏加载提示,自行处理
            serverSide: true, //启用服务器端分页
            paging: true,//启用本地分页
            searching: false, //禁用原生搜索
            iDisplayLength: opts.pageSize || 10,//每页显示数量
            iDisplayStart: 0,//从第几页开始
            pagingType: "full_numbers", //分页样式：simple,simple_numbers,full,full_numbers
            lengthChange: false,
            ordering: opts.ordering||false,//开启排序
            info: true,
            order: opts.order||[],
            columnDefs: opts.columnDefs||[],
            "ajax": function (data, callback, settings) {
                //封装请求参数
                var param = opts.param || {};
                param.pageSize = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                param.pageNum = (data.start / data.length) + 1;//当前页码
                //排序参数
                if(data.order&&data.order.length>0){
                    param.sortDir = data.columns[data.order[0].column].data + " " + data.order[0].dir;
                }
                //ajax请求数据
                $.ajax({
                    type: "POST",
                    url: opts.url || "",
                    cache: false, //禁用缓存
                    data: param, //传入组装的参数
                    dataType: "json",
                    success: function (result) {
                        //封装返回数据
                        var returnData = {};
                        returnData.recordsTotal = result.total;//返回数据全部记录
                        returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.list;//返回的数据列表
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    }
                });
            },
            columns: opts.columns
        });

        $('#'+tableId+' tbody').on( 'click', 'tr', function () {
            if(! (opts.multiple || false)){//单选
                if(!$(this).hasClass('selected')){
                    $(this).parent().find('.selected').removeClass('selected');
                }
            }
            $(this).toggleClass('selected');
        } );

        return t;
    };

})();

//js function
function layerAlert(msg) {
    top.layer.alert(msg, {
        skin: 'layui-layer-lan'
        ,closeBtn: 0
    });
}


