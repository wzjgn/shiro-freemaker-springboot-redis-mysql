var ScadaPlcDcsManaged = function () {
 
	
    return {

        //main function to initiate the module
        init: function () {
        	 
            if (!jQuery().dataTable) {
                return;
            }

            // begin first table
            $('#sample_1').dataTable({
            	 "bProcessing": true,
        		 "bServerSide": true,
        		 "bAutoWidth": true,
            	"sAjaxSource": "/admin/findScadaPlcDcs",
                "aoColumns": [
                             { "mData": "companyFullName"} ,
                             { "mData": "systemName"},
                             { "mData": "functionDesc"},
                             { "mData": "useDepartemnt"},
                             { "mData": "status"},
                             { "mData": "type"},
                             { "mData": "createTime" } 
                             
                ],
                 
                // set the initial value
                "aLengthMenu": [
                                [5, 15, 20],
                                [5, 15, 20] // change per page values here
                            ],
                "iDisplayLength":5,
                //"sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
               // "sPaginationType": "full_numbers",   
                "oLanguage": {//下面是一些汉语翻译
                    "sSearch": "搜索",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "没有检索到数据",
                    "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
                    "sInfoFiltered": "(筛选自 _MAX_ 条数据)",
                    "sInfoEmtpy": "没有数据",
                    "sProcessing": "正在加载数据...",
                            "oPaginate":
                            {
                                "sFirst": "首页",
                                "sPrevious": "前一页",
                                "sNext": "后一页",
                                "sLast": "末页"
                            },
                            "sZeroRecords": "没有检索到数据"
                }
                
            });

            jQuery('#sample_1 .group-checkable').change(function () {
                var set = jQuery(this).attr("data-set");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).attr("checked", true);
                    } else {
                        $(this).attr("checked", false);
                    }
                });
                jQuery.uniform.update(set);
            });

            jQuery('#sample_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#sample_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            //jQuery('#sample_1_wrapper .dataTables_length select').select2(); // initialzie select2 dropdown

        }

    };

}();