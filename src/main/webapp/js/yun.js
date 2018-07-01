/**
 * Created by qiangxl on 2018/6/14 0014.
 */
/*侧边栏和主体内容的tab切换*/
$(function () {
    const UPFILE_KEY = "upFile"; // key
    const UPFILE_VALUE = "upFile"; // value

    // 判断刷新之前是否有上传文件
    if (sessionStorage.getItem(UPFILE_KEY) != null) {
        // 发送请求看看文件上传成功了没
        $.ajax({
            url: "getUpStatus.do",
            type: "GET",
            success: function (resp) {
                alert(resp.msg);
            },
            error: function () {
                alert("网络错误！不知道文件是否上传成功！");
            }
        });

        // 清除缓存
        sessionStorage.removeItem(UPFILE_KEY);
    }

    let count = 0;
    let id_list = [];
    let activeTabs = sessionStorage.getItem("activeTabs");
    if (activeTabs == null) {
        activeTabs = 2;
    }
    let deleteUrls = [];
    $('#_menu_list li:eq("1")').addClass('cur').show();
    $('#_main_body div.mod-list-group').hide();
    $('#_main_body div.mod-list-group:eq("1")').show();

    $('#_menu_list li').click(function () {
        $('#_menu_list li').removeClass("cur");
        $(this).addClass('cur');
        /*let type = $.trim($(this).children('a').text());
        alert(type);*/
        $('#_main_body div.mod-list-group').hide();
        let activeTab = $(this).find('a').attr('data-type');
        //获取当前被点击的tab的data-type
        id_list = $("#_menu_list li");
        for (let i = 0; i < id_list.length; i++) {
            if ($(id_list[i]).attr("class") == 'cur') {
                activeTabs = $(id_list[i]).find("a").attr("data-type");
                console.log("当前被点击的" + activeTabs);
            }
        }

        //通过类型来获得对应类型的列表

        $("#_main_body [data-type=" + activeTab + "]").show();
        if (activeTab == 2) {
            getFileName(null, activeTab);
        } else {
            getFileNameByType(activeTab);
        }
    });

    //跳转tab
    function reloadTab(type) {
        $('#_menu_list li').removeClass("cur");
        $("#_menu_list li a[data-type=" + type + "]").parent().addClass('cur');
        console.log($("#_menu_list li a[data-type=" + type + "]").parent());
        $('#_main_body div.mod-list-group').hide();
        //通过类型来获得对应类型的列表

        $("#_main_body [data-type=" + type + "]").show();
        console.log("222222222222222");
        console.log("roloadFunction----" + type);
        if (type == 2) {
            getFileName(null, type);
        } else {
            getFileNameByType(type);
        }
    };

    reloadTab(activeTabs);

    //跳转
    $(document).on("click", '.item-tit a', function () {
        //title用数据库中的filename
        //到时候就用这个，title来获取，是哪个文件，然后用来跳转到指定的页面
        let $title1 = $(this).attr('title');
        let type = $(this).parent().parent().parent().parent().parent().parent().parent().attr("data-type");
        let fileType = $(this).attr("data-file");
        if (fileType == "folder") {
            $.ajax({
                type: "POST",
                url: "getFileList.do",
                data: {
                    "fileName": $title1,
                    "back": 0,
                },
                success: function (response) {
                    if (response.data == null || response.data.length == 0) {
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
                    else {
                        let app1 = new Vue({
                            el: '#app1',
                            data: response,
                            methods: {
                                showUpdateTime: function () {
                                    const files = this.data;
                                    if (files == null) {
                                        return;
                                    }

                                    for (let i = 0; i < files.length; i++) {
                                        files[i].updateTime = new Date(files[i].updateTime).toLocaleString();
                                    }
                                }
                            }
                        });
                        app1.showUpdateTime();
                        console.log(response.data);
                        window.location.reload();
                    }
                    console.log(response.data);
                },
                error: function () {
                    console.log("失败");
                },
            });
        }
        $("#back").show();
        //$(this).attr('href',"https://www.weiyun.com/disk");
    });

    $("#back").click(function () {
        $.ajax({
            type: "POST",
            url: "getFileList.do",
            data: {
                "back": 1,
            },
            success: function (response) {
                if (response.data == null || response.data.length == 0) {
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
                else {
                    let app1 = new Vue({
                        el: '#app1',
                        data: response,
                        methods: {
                            showUpdateTime: function () {
                                const files = this.data;
                                if (files == null) {
                                    return;
                                }

                                for (let i = 0; i < files.length; i++) {
                                    files[i].updateTime = new Date(files[i].updateTime).toLocaleString();
                                }
                            }
                        }
                    });
                    app1.showUpdateTime();
                }
                console.log(response.data);
                window.location.reload();
            },
            error: function () {
                console.log("失败");
            },
        });
    });

    //文件上传
    $("#upload").click(function () {
        // 刷新页面之前在 sessionStorage 加入信息，提示浏览器刷新之后要验证文件上传是否成功
        sessionStorage.setItem(UPFILE_KEY, UPFILE_VALUE);
        $("#File").click();
    });

    //新建
    $('#plus').click(function () {
        $('#plus_modal').modal({
            keyboardShortcuts: false,
            closable: false,
        }).modal('show');
    });
    $('#plus_ensure').click(function () {
        //如果输入框为空，说明没有输入文件夹的名字。就不让它继续
        if ($('input[name="fileName"]').val() == "") {
            $('input[name="fileName"]').attr('placeholder', "文件名不能为空");
            //说明输入了文件夹的名字
        } else {
            $('#plus_modal').modal('hide');
            let folderName = $('input[name="fileName"]').val();
            createNewFolder(folderName);
        }
    });
    $('#plus_console').click(function () {
        $('#plus_modal').modal('hide');
    });


    //下载
    let downFileNames = "";
    $('#download').click(function () {
        let downloadArrays = $('.item-icon input[data-type-click="true"]');
        for (let i = 0; i < downloadArrays.length; i++) {
            let downFileName = "fileNames=" + $(downloadArrays[i]).attr("data-type-name") + "&";
            downFileNames += downFileName;
        }
        downFileNames = downFileNames.substring(0, downFileNames.lastIndexOf("&"));
        let url = document.location.href;
        let downUrl = url.substring(0, url.lastIndexOf("/"));
        downUrl = downUrl + "/multipleDownload.do?" + downFileNames;
        window.open(downUrl, "_blank");
        downFileNames = "";
    });
    let renameArrays = [];
    //重命名
    $("#rename").click(function () {
        renameArrays = $('.item-icon input[data-type-click="true"]');
        if (renameArrays.length > 1) {
            $('#rename_modal').modal({
                keyboardShortcuts: false,
                closable: false,
            }).modal('show');
            renameArrays = [];
        } else {
            $("#rename_modal_true").modal({
                keyboardShortcuts: false,
                closable: false,
            }).modal("show");
        }

        $("#rename_modal_true_ensure").click(function () {
            let preName = $(renameArrays).attr("data-type-name");
            let suffix = preName.substr(preName.lastIndexOf("."));
            let fileName = $("#rename_modal_true input[name='renameFile']").val();
            if (fileName == "") {
                $("#rename_modal_true input[name='renameFile']").attr("placeholder", "文件名不能为空");
            }
            else {
                $("#rename_modal_true").modal("hide");
                let uri = $(renameArrays).attr("name");
                fileName = fileName + suffix;
                renameFile(uri, fileName);
            }
        });

        $('#rename_modal_true_console').click(function () {
            $("#rename_modal_true").modal("hide");
        });
        renameFile = function (uri, fileName) {
            $.ajax({
                type: "POST",
                url: "renameFile.do",
                data: {
                    "partUri": uri,
                    "fileName": fileName,
                },
                success: function (response) {
                    sessionStorage.setItem("activeTabs", activeTabs);
                    window.location.reload();
                },
                error: function () {
                    console.log("rename 失败");
                },
            });
        };

    });
    $('#rename_ensure').click(function () {
        $('#rename_modal').modal('hide');
    });

    //删除
    $('#delete').click(function () {
        let deleteArrays = $('.item-icon input[data-type-click="true"]');
        for (let i = 0; i < deleteArrays.length; i++) {
            deleteUrls.push($(deleteArrays[i]).attr("name"));
        }
        if (count >= 2) {
            $('#delete_modal .content .ui.header').text("你确定要删除这些文件(夹)?");
        }
        $('#delete_modal').modal({
            keyboardShortcuts: false,
            closable: false,
        }).modal('show');
    });
    $('#delete_ensure').click(function () {
        $('#delete_modal').modal('hide');
        $.ajax({
                type: "POST",
                url: "deleteFile.do",
                data: {"urls": deleteUrls},
                traditional: true,
                success: function (response) {
                    sessionStorage.setItem("activeTabs", activeTabs);
                    window.location.reload();
                },
                error: function () {
                    console.log("删除操作失败了,就很烦");
                },
            }
        );
    });
    $('#delete_console').click(function () {
        deleteUrls = [];
        $('#delete_modal').modal('hide');
    });

    function getFileName(fileName, type, back = 0) {
        $.ajax({
            url: 'getFileList.do',
            type: 'GET',
            data: {
                "fileName": fileName,
                "back": back,
            },
            success: function (response) {
                if (response.data == null || response.data.length == 0) {
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
                else {
                    let app1 = new Vue({
                        el: '#app1',
                        data: response,
                        methods: {
                            showUpdateTime: function () {
                                const files = this.data;
                                if (files == null) {
                                    return;
                                }

                                for (let i = 0; i < files.length; i++) {
                                    files[i].updateTime = new Date(files[i].updateTime).toLocaleString();
                                }
                            }
                        }
                    });
                    app1.showUpdateTime();
                    console.log(response.data);
                }
            },
            error: function () {
                console.log("错误");
            }
        });
    };

    function createNewFolder(folderName) {
        $.ajax({
            url: 'newFolder.do',
            type: 'GET',
            data: {
                'folderName': folderName,
            },
            success: function (response) {
                if (response.status == 0) {
                    sessionStorage.setItem("activeTabs", activeTabs);
                    window.location.reload();
                }
                console.log(response);
            },
            error: function () {
                console.log("错误");
            },

        });
    };
    getFileName(null, "2");

    function getFileNameByType(type) {
        $.ajax({
            type: "POST",
            url: "listFilesByType.do",
            data: {
                "type": type,
            },
            success: function (response) {
                if (response.data == null || response.data.length == 0) {
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
                } else {
                    let _app = "#app-" + type;
                    let app1 = new Vue({
                        el: _app,
                        data: response,
                        methods: {
                            showUpdateTime: function () {
                                const files = this.data;
                                if (files == null) {
                                    return;
                                }

                                for (let i = 0; i < files.length; i++) {
                                    files[i].updateTime = new Date(files[i].updateTime).toLocaleString();
                                }
                            }
                        }
                    });
                    app1.showUpdateTime();
                    console.log(response);
                }
            },
            error: function () {
                console.log("getFileNameByType失败");
            },
        });
    };

    //因为Dom元素是用ajax异步请求的，所以要用动态加载元素绑定事件
    // 点击打勾，显示下载，分享，删除，更多的button,count用来表示当最后一个勾取消时，button消失
    $(document).on('click', '.item-icon input', function () {
        if ($(this).parent().parent().attr('class') == 'list-group-item act') {
            count--;
            if (count == 0) {
                $('div.ui.icon.basic.buttons').css({
                    'display': 'none',
                });
            }
            $(this).parent().parent().removeClass('act');
            $(this).attr("data-type-click", "false");
        } else {
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

