// 一加载完页面就执行脚本
// 大界面的切换器
const mainSwiper = new Swiper('#mainSwiper', {
    allowTouchMove: false, // 只能通过点击导航栏来切换，不允许直接切换
    autoHeight: true, // enable auto height
});

// 笔记界面的切换器
const repoSwiper = new Swiper('#repoSwiper', {
    allowTouchMove: false, // 只能通过点击导航栏来切换，不允许直接切换
    direction: 'vertical',
});

// 笔记内部界面的切换器
const noteBookListSwiper = new Swiper('#notebookListSwiper', {
    allowTouchMove: false, // 只能通过点击导航栏来切换，不允许直接切换
});

// 笔记内部右边界面的切换器
const noteListSwiper = new Swiper('#noteListSwiper', {
    allowTouchMove: false, // 只能通过点击导航栏来切换，不允许直接切换
});

// 笔记内容编辑器（富文本）
const wangEditor = window.wangEditor;
const editor = new wangEditor("#editorTool", "#editorBox");
// 自定义菜单配置
editor.customConfig.menus = [
    'head',  // 标题
    'bold',  // 粗体
    'fontSize',  // 字号
    'fontName',  // 字体
    'italic',  // 斜体
    'underline',  // 下划线
    'strikeThrough',  // 删除线
    'foreColor',  // 文字颜色
    'backColor',  // 背景颜色
    'link',  // 插入链接
    'list',  // 列表
    'justify',  // 对齐方式
    'quote',  // 引用
    'emoticon',  // 表情
    'image',  // 插入图片
    'table',  // 表格
    'code',  // 插入代码
    'undo',  // 撤销
    'redo'  // 重复
];
editor.create();

$(function () {

    // 点击导航栏样式切换
    const navBtns = $(".navBtn li");

    // 切换菜单速度，单位：ms
    const SLIDE_SPEED = 800;

    // 从 sessionStorage 中获取登陆信息时用到的 key 值
    const LOGIN_KEY = "isLogin";

    // 从 sessionStorage 中获取验证刷新页面信息时用到的 key 值
    const CHECK_LOGIN_KEY = "checkLogin";

    // 将笔记本总页数放入 sessionStorage 中
    const MAX_PAGE_NUMBER_OF_NOTEBOOK_KEY = "maxPageNumberOfNotebook";

    // 将当前笔记本的笔记总页数放入 sessionStorage 中
    const MAX_PAGE_NUMBER_OF_NOTE_KEY = "maxPageNumberOfNote";

    // 得到笔记本总页数
    function getMaxPageOfNotebook() {
        return Number(sessionStorage.getItem(MAX_PAGE_NUMBER_OF_NOTEBOOK_KEY));
    }

    // 设置笔记本总页数
    function setMaxPageOfNotebook(number) {
        sessionStorage.setItem(MAX_PAGE_NUMBER_OF_NOTEBOOK_KEY, number);
    }

    // 得到笔记总页数
    function getMaxPageOfNote() {
        return Number(sessionStorage.getItem(MAX_PAGE_NUMBER_OF_NOTE_KEY));
    }

    // 设置笔记总页数
    function setMaxPageOfNote(number) {
        sessionStorage.setItem(MAX_PAGE_NUMBER_OF_NOTE_KEY, number);
    }

    // 当登陆之后，将登陆信息存在 localStorage 中
    function loginSuccess() {
        sessionStorage.setItem(LOGIN_KEY, "true");
    }

    // 如果原本没有检查标志，说明是第一次打开页面，这时候就直接设置标识
    if (sessionStorage.getItem(CHECK_LOGIN_KEY) == null) {
        sessionStorage.setItem(CHECK_LOGIN_KEY, "false");
    }

    // 判断是否登陆的方法
    function isLogin() {
        if (sessionStorage.getItem(LOGIN_KEY) == null) {
            return false;
        }

        // 发送请求去查看 session 是否有登陆信息
        $.ajax({
            url: "user/is_login.do",
            type: "POST",
            success: function (resp) {
                // 没有登陆
                if (!(resp.status === 0)) {
                    sessionStorage.removeItem(LOGIN_KEY);

                    if (!(sessionStorage.getItem(CHECK_LOGIN_KEY) === "true")) {
                        window.location.reload();
                        sessionStorage.setItem(CHECK_LOGIN_KEY, "true");
                    }
                }
            }
        });

        return sessionStorage.getItem(LOGIN_KEY) === "true";
    }

    // 清除原来的选择样式，并给指定元素加上样式
    function removeAndAddClass(ele) {
        const navBtns = $(".navBtn li");
        // 先清除原本留有的样式
        for (let j = 0; j < navBtns.length; j++) {
            $(navBtns[j]).removeClass("chosenLi");
        }

        // 再给这个被点击的加上
        $(ele).addClass("chosenLi");
    }

    // 切换到登陆界面
    function slideToLoginPage() {
        mainSwiper.slideTo(1, SLIDE_SPEED);
        repoSwiper.slideTo(1, SLIDE_SPEED);

        // 导航栏也要切换
        removeAndAddClass(navBtns[1]);
    }

    // 切换到注册界面
    function slideToRegisterPage() {
        mainSwiper.slideTo(1, SLIDE_SPEED);
        repoSwiper.slideTo(0, SLIDE_SPEED);

        // 导航栏也要切换
        removeAndAddClass(navBtns[1]);
    }

    // 切换到仓库界面
    function slideToRepoPage() {
        mainSwiper.slideTo(1, SLIDE_SPEED);
        repoSwiper.slideTo(2, SLIDE_SPEED);

        // 导航栏也要切换
        removeAndAddClass(navBtns[1]);

        // 获得笔记本列表
        getNoteBookList();
    }

    // 切换到个人信息页面
    function slideToSettingPage() {
        mainSwiper.slideTo(2, SLIDE_SPEED);

        // 导航栏也要切换
        removeAndAddClass(navBtns[2]);
    }

    // 切换到首页
    function slideToWelcomePage() {
        mainSwiper.slideTo(0, SLIDE_SPEED);

        // 导航栏也要切换
        removeAndAddClass(navBtns[0]);
    }

    // 切换显示资料和修改资料的模块
    function switchShowOrUpdate() {
        if (!isLogin()) {
            // 还没有登陆，跳转到登陆界面
            slideToLoginPage();
            return;
        }

        const detailDiv = $($(".detail")[0]);
        const formDiv = $($(".form")[0]);

        // 如果它原本是隐藏的，就显示出来，相反，就显示出来
        if (detailDiv.hasClass("hidden")) {
            detailDiv.removeClass("hidden");
            formDiv.addClass("hidden");
        } else {
            formDiv.removeClass("hidden");
            detailDiv.addClass("hidden");
        }
    }

    for (let i = 0; i < navBtns.length; i++) {
        $(navBtns[i]).click(function () {
            // 清除原来的样式，然后给当前元素加上样式
            removeAndAddClass(this);

            // 切换到指定页面
            mainSwiper.slideTo(i, SLIDE_SPEED);
            if (i === 1) {
                // 获取笔记本列表
                getNoteBookList();
            }

            if (i === 1) {
                repoSwiper.slideTo(1, SLIDE_SPEED);
            }

            if (isLogin() === true) {
                repoSwiper.slideTo(2, SLIDE_SPEED);
            }
        });
    }

    // 给跳转到登陆界面的按钮绑定事件
    $(".slideToLoginPageBtn").click(slideToLoginPage);

    // 给跳转到注册界面的按钮绑定事件
    $(".slideToRegiterPageBtn").click(slideToRegisterPage);

    // 给切换按钮添加事件
    $(".switchBtn").click(switchShowOrUpdate);

    // 用户业务
    // 给用户名输入框加样式，设置错误信息
    function showError(msg) {
        const error = $("#usernameError");
        error.text(msg); // 设置错误信息
        error.show(); // 显示错误信息

        $("#inputUsername").addClass("inputError").focus(function () {
            $(this).removeClass("inputError");
            error.hide();
        });
    }

    // 注册按钮绑定事件
    $("#registerBtn").click(function () {
        const form = $("#registerForm"); // 获取到表单信息
        const pwd = $("#inputPwd"); // 获取密码输入框
        const ensurePwd = $("#inputEnsurePwd"); // 获取用户输入的确认密码输入框

        if (pwd.val() === ensurePwd.val()) {
            // 密码和确认密码相等
            if ($("#inputUsername").hasClass("inputError")) {
                // 如果用户名验证有错误，则提示用户处理
                alert("请处理错误再注册！");
                return;
            }

            // 发送注册请求
            $.ajax({
                url: "user/register.do",
                type: "POST",
                data: form.serialize(),
                success: function (resp) {
                    if (resp.status === 0) {
                        alert(resp.msg); // 提示注册成功
                        slideToLoginPage(); // 跳转到登陆界面
                    } else {
                        showError(resp.msg);
                    }
                },
                error: function () {
                    showError("网络错误！");
                }
            });
        } else {
            // 密码和确认密码不相等
            $("#pwdError").show();
            pwd.addClass("inputError");
            ensurePwd.addClass("inputError");

            // 当密码不匹配时，会触发红色警告，这个方法可以移除警告
            function hideInputError() {
                pwd.removeClass("inputError");
                ensurePwd.removeClass("inputError");
                $("#pwdError").hide();
            }

            // 当再次点击输入框的时候移除警告样式
            pwd.focus(hideInputError);
            ensurePwd.focus(hideInputError);
        }
    });

    // 验证用户名，当用户名改变的时候
    $("#inputUsername").on("change", function () {
        // 发送请求去验证用户名
        $.ajax({
            url: "user/check_username.do",
            type: "POST",
            data: $("#registerForm").serialize(),
            success: function (resp) {
                if (!(resp.status === 0)) {
                    // 说明用户名不可用，加样式
                    showError(resp.msg);
                }
            },
            error: function () {
                showError("网络错误。。。");
            }
        });
    });

    // 将信息填充到设置页面
    function putInfoOnSettingPage(data) {
        // 获取设置页面的控件，进行设置
        if (data.username != null) {
            $("#usernameSpan").text(data.username);
            $("#usernameId").text(data.username);
        }

        if (data.name != null) {
            $("#nameSpan").text(data.name);
            $("#nameId").val(data.name);
        }

        if (data.qq != null) {
            $("#qqSpan").text(data.qq);
            $("#qqId").val(data.qq);
        }

        if (data.mail != null) {
            $("#mailSpan").text(data.mail);
            $("#mailId").val(data.mail);
        }

        if (data.intro != null) {
            $("#introSpan").text(data.intro);
            $("#introId").val(data.intro);
        }
    }

    // 登录功能
    $("#loginBtn").click(function () {
        // 发送请求登陆
        $.ajax({
            url: "user/login.do",
            type: "POST",
            data: $("#loginForm").serialize(),
            success: function (resp) {
                // 把错误消息显示出来
                if (!(resp.status === 0)) {
                    alert(resp.msg);
                }

                if (resp.status === 0) {
                    // 登陆成功
                    loginSuccess();

                    // 设置个人信息到页面上
                    putInfoOnSettingPage(resp.data);

                    // 修改首页按钮为跳转到笔记界面
                    $(".welcomePage .operation").hide();
                    $(".welcomePage .afterLogin").show();

                    // 登陆界面和注册界面要被隐藏
                    slideToRepoPage();

                    // 跳转到首页
                    slideToWelcomePage();
                }
            }
        });
    });

    // 退出登录按钮事件
    function logout() {
        // 没有登陆还退个屁啊。。。
        if (!isLogin()) {
            return;
        }

        $.ajax({
            url: "user/logout.do",
            type: "GET",
            success: function (resp) {
                // 当服务器端成功退出登陆，才对客户端进行退出登陆操作
                if (resp.status === 0) {
                    sessionStorage.removeItem(LOGIN_KEY);
                    sessionStorage.removeItem(CHECK_LOGIN_KEY);

                    // 刷新页面
                    window.location.reload();
                }
            },
            error: function () {
                alert("网络错误！");
            }
        });
    }

    // 给退出登录按钮加响应事件
    $(".logout").click(logout);

    // 给跳转到笔记界面设置点击事件
    $("#turnToNotebook").click(slideToRepoPage);

    // 得到个人资料
    function getInfo() {
        $.ajax({
            url: "user/get_info.do",
            type: "GET",
            success: function (resp) {
                putInfoOnSettingPage(resp.data);
            },
            error: function () {
                alert("网络错误！");
            }
        });
    }

    // 如果是登陆了，就获取信息
    if (isLogin()) {
        getInfo();

        // 修改首页按钮为跳转到笔记界面
        $(".welcomePage .operation").hide();
        $(".welcomePage .afterLogin").show();
    }

    // 修改个人资料
    $("#saveBtn").click(function () {
        if (isLogin()) {
            // 先判断邮箱是否合法
            if (isEmail($("#mailId").val())) {
                $.ajax({
                    url: "user/update_info.do",
                    type: "POST",
                    data: $("#settingForm").serialize(),
                    success: function (resp) {
                        if (!(resp.status === 0)) {
                            alert(resp.msg);
                        } else {
                            // 修改成功之后刷新前台数据
                            getInfo();

                            // 切换界面
                            switchShowOrUpdate();
                        }
                    },
                    error: function () {
                        alert("网络错误！");
                    }
                });
            } else {
                alert("邮箱不合法");
            }
        }

        return false;
    });

    // 验证邮箱
    function isEmail(str) {
        const reg = /^\w+@\w+\.\w{2,}$/; // 邮箱正则表达式

        // 如果是邮箱，返回 true
        return reg.test(str);
    }

    // 切换笔记本名称和输入笔记本名称
    function switchManageNotebook() {
        const hiddenPs = $(".notebookPan p[class='hidden']");
        const showPs = $(".notebookPan p[class!='hidden']");

        // 将隐藏的显示出来
        hiddenPs.each(function (index, item) {
            $(item).removeClass("hidden");
        });

        // 将显示的隐藏掉
        showPs.each(function (index, item) {
            $(item).addClass("hidden");
        });

        $("#notebookCreateTimeP").removeClass("hidden");

        // 让输入框的内容和标题的一致
        $("#notebookNameInput").val($("#notebookNameP").text());
    }

    // 给切换按钮绑定切换响应事件
    $("#switchManageNotebookBtn").click(switchManageNotebook);
    $("#cancelNotebookNameBtn").click(switchManageNotebook);

    // 给新建笔记本和新建笔记添加输入框事件
    $("#addANewNotebookBtn").click(function () {
        // 新建笔记本输入框
        const newNotebookName = prompt("请输入新建笔记本的名称：", "新建笔记本 " + new Date().toLocaleString());

        // 当用户点击取消时返回 null
        if (newNotebookName == null) {
            return;
        }

        // 判断笔记本名称是否为空
        if (newNotebookName.trim() === "") {
            alert("笔记本名称不能为空！");
            return;
        }

        // 新建笔记本
        $.ajax({
            url: "notebook/insert_notebook.do",
            type: "POST",
            data: {
                "notebookName":newNotebookName
            },
            success: function (resp) {
                if (resp.status === 0) {
                    // 添加成功就刷新列表
                    getNoteBookList();
                } else {
                    alert(resp.msg);
                }
            },
            error: function () {
                alert("网络错误！");
            }
        });
    });

    $("#addANewNoteBtn").click(function () {
        // 新建笔记本输入框
        const newNoteName = prompt("请输入新建笔记的名称：", "新建笔记 " + new Date().toLocaleString());

        // TODO 新建笔记
        console.log(newNoteName);
    });

    // 打开笔记内容区域
    function slideToNote() {
        // 切换选项卡到笔记内容区域
        noteListSwiper.slideTo(1, SLIDE_SPEED);

        // 切换笔记本列表到笔记列表
        noteBookListSwiper.slideTo(1, SLIDE_SPEED);
    }

    // 打开笔记列表区域
    function slideToNoteList() {
        // 切换选项卡到笔记列表区域
        noteListSwiper.slideTo(0, SLIDE_SPEED);

        // 切换笔记列表到笔记本列表
        noteBookListSwiper.slideTo(0, SLIDE_SPEED);
    }

    // 返回笔记列表
    $("#backToNoteListBtn").click(slideToNoteList);

    // 当前访问的笔记本 id
    let currentNotebookId = "";

    // 将获取到的笔记本列表加载到页面
    const noteBookListUl = $("#noteBookListUl");
    function putNoteBookListOnPage(data) {
        if (data == null) {
            return;
        }

        // 放数据之前先清空列表
        noteBookListUl.empty();

        // 遍历拿到的笔记本列表
        for (let i = 0; i < data.length; i++) {
            let li = $("<li></li>").append(data[i].notebookName);
            li.attr("notebookId", data[i].notebookId).attr("notebookCreateTime", data[i].notebookCreateTime);
            li.appendTo(noteBookListUl);
        }
    }

    // 获取这个用户的所有笔记本
    function getNoteBookList() {
        if (!isLogin()) {
            return;
        }

        // 有登陆才去获取
        $.ajax({
            url: "notebook/show_notebook_of_userId.do",
            type: "POST",
            data: {
                "page":noteBookListUl.attr("currentPage")
            },
            success: function (resp) {
                // 如果获取成功就显示
                if (resp.status === 0) {
                    putNoteBookListOnPage(resp.data.list);

                    // 设置总页数
                    setMaxPageOfNotebook(resp.data.lastPage);

                    // 为笔记列表添加点击事件
                    const noteBookListUlLis = $("#noteBookListUl li");
                    noteBookListUlLis.each(function (index, item) {
                        $(item).click(function () {
                            // 先清除原来的样式
                            $("#noteBookListUl li.noteBookListChosen").removeClass("noteBookListChosen");

                            // 再给被点击的 li 加样式
                            $(item).addClass("noteBookListChosen");

                            // 更新笔记界面
                            updateNotePage(this);
                        });
                    });
                }
            },
            error: function () {
                alert("网络错误！");
            }
        });
    }

    // 打开一本笔记
    function getANewNote() {

    }

    // 为笔记列表添加点击事件
    const rightNoteBookListUlLis = $("#rightNoteBookListUl li");
    rightNoteBookListUlLis.each(function (index, item) {
        $(item).click(function () {
            // 打开笔记内容区域
            slideToNote();

            // 显示一本笔记内容
            getANewNote();
        });
    });

    // 更新右侧笔记界面
    function updateNotePage(notebookLi) {
        notebookLi = $(notebookLi);

        // 这个按钮可以重见天日了
        $("#switchManageNotebookBtn").removeClass("hidden");

        // 更新当前访问的笔记本 id
        currentNotebookId = notebookLi.notebookid;

        $("#notebookNameP").text(notebookLi.text());
        $("#notebookCreateTimeP").text(new Date(Number(notebookLi.attr("notebookcreatetime"))).toLocaleString());
    }
});