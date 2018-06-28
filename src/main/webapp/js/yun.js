/**
 * Created by qiangxl on 2018/6/14 0014.
 */
/*侧边栏和主体内容的tab切换*/
$(function ()
{
    var count = 0;
    var id_list = new Array();
    let deleteUrls = [];
    $('#_menu_list li:eq("1")').addClass('cur').show();
    $('#_main_body div.mod-list-group').hide();
    $('#_main_body div.mod-list-group:eq("1")').show();

    $('#_menu_list li').click(function ()
    {
        $('#_menu_list li').removeClass("cur");
        $(this).addClass('cur');
        /*let type = $.trim($(this).children('a').text());
        alert(type);*/
        $('#_main_body div.mod-list-group').hide();
        var activeTab = $(this).find('a').attr('data-type');
        $("#_main_body [data-type=" + activeTab + "]").show();
        getFileName(null, activeTab);
    });

    //跳转
    $('.item-tit a').click(function ()
    {
        //title用数据库中的filename
        //到时候就用这个，title来获取，是哪个文件，然后用来跳转到指定的页面
        var $title1 = $(this).attr('title');
        //$(this).attr('href',"https://www.weiyun.com/disk");
    });

    //文件上传

    $("#upload").click(function ()
    {
        $("#File").click();
    });

    //新建
    $('#plus').click(function ()
    {
        $('#plus_modal').modal({
            keyboardShortcuts: false,
            closable: false,
        }).modal('show');
    });
    $('#plus_ensure').click(function ()
    {
        //如果输入框为空，说明没有输入文件夹的名字。就不让它继续
        if ($('input[name="fileName"]').val() == "")
        {
            $('input[name="fileName"]').attr('placeholder', "文件名不能为空");
            //说明输入了文件夹的名字
        } else
        {
            $('#plus_modal').modal('hide');
            let folderName = $('input[name="fileName"]').val();
            createNewFolder(folderName);
            alert("创建成功");
            alert(folderName);
        }
    });
    $('#plus_console').click(function ()
    {
        $('#plus_modal').modal('hide');
    });


    //下载
    let downFileName = [];
    $('#download').click(function ()
    {
        let downloadArrays = $('.item-icon input[data-type-click="true"]');
        for (let i = 0; i < downloadArrays.length; i++)
        {
            downFileName.push($(downloadArrays[i]).attr("data-type-name"));
        }
        console.log("下载开始");
        console.log(downFileName);
    });

    //删除
    $('#delete').click(function ()
    {
        let deleteArrays = $('.item-icon input[data-type-click="true"]');
        for (let i = 0; i < deleteArrays.length; i++)
        {
            deleteUrls.push($(deleteArrays[i]).attr("name"));
            console.log("我是删除里面的" + $(deleteArrays[i]).attr("name"));
        }
        console.log(deleteUrls);
        if (count >= 2)
        {
            $('#delete_modal .content .ui.header').text("你确定要删除这些文件(夹)?");
        }
        $('#delete_modal').modal({
            keyboardShortcuts: false,
            closable: false,
        }).modal('show');
    });
    $('#delete_ensure').click(function ()
    {
        $('#delete_modal').modal('hide');
        $.ajax({
                type: "POST",
                url: "deleteFile.do",
                data: {"urls":deleteUrls},
                traditional: true,
                success: function (response)
                {
                    console.log(response);
                    window.location.reload();
                },
                error: function ()
                {
                    console.log("删除操作失败了,就很烦");
                },
            }
        );
    });
    $('#delete_console').click(function ()
    {
        deleteUrls = [];
        $('#delete_modal').modal('hide');
    });
    getFileName = function (fileName, type)
    {
        $.ajax({
            url: 'getFileList.do',
            type: 'GET',
            data: {
                "fileName": fileName,
            },
            success: function (response)
            {
                if (response.data == null || response.data.length == 0)
                {
                    $('.mod-list-group[data-type=' + type + ']').html("<div class=\"mod-status\">\n" +
                        "                                <div class=\"empty-box\">\n" +
                        "                                    <div class=\"status-inner\">\n" +
                        "                                        <i class=\"icon image\">\n" +
                        "                                        </i>\n" +
                        "                                    </div>\n" +
                        "                                    <h3 class=\"ui header\">暂无文件</h3>\n" +
                        "                                    <h5 class=\"ui header\">请点击左上角的\"上传\"按钮添加</h5>\n" +
                        "                                </div>\n" +
                        "                            </div>")
                }
                let app1 = new Vue({
                    el: '#app1',
                    data: response,
                    methods: {
                        showUpdateTime: function ()
                        {
                            const files = this.data;
                            if (files == null)
                            {
                                return;
                            }

                            for (let i = 0; i < files.length; i++)
                            {
                                files[i].updateTime = new Date(files[i].updateTime).toLocaleString();
                            }
                        }
                    }
                });
                app1.showUpdateTime();
                console.log(response.data);
            },
            error: function ()
            {
                console.log("错误");
            }
        });
    };
    createNewFolder = function (folderName)
    {
        $.ajax({
            url: 'newFolder.do',
            type: 'GET',
            data: {
                'folderName': folderName,
            },
            success: function (response)
            {
                if (response.status == 0)
                {
                    window.location.replace('http://localhost:8080/Jotter/yun.html');
                }
                console.log(response);
            },
            error: function ()
            {
                console.log("错误");
            },

        });
    };
    getFileName(null, "2");

    //因为Dom元素是用ajax异步请求的，所以要用动态加载元素绑定事件
    // 点击打勾，显示下载，分享，删除，更多的button,count用来表示当最后一个勾取消时，button消失
    $(document).on('click', '.item-icon input', function ()
    {
        if ($(this).parent().parent().attr('class') == 'list-group-item act')
        {
            count--;
            if (count == 0)
            {
                $('div.ui.icon.basic.buttons').css({
                    'display': 'none',
                });
            }
            $(this).parent().parent().removeClass('act');
            $(this).attr("data-type-click", "false");
        } else
        {
            count++;
            $(this).parent().parent().addClass('act');
            $(this).attr("data-type-click", "true");
            $('div.ui.icon.basic.buttons').css({
                'display': 'inline-flex',
                'margin-left': '10px',
            });
            console.log($(this).attr('name'));
        }
    });
});

