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

let isLogin = false; // 判断是否登陆

$(function () {

    // 点击导航栏样式切换
    const navBtns = $(".navBtn li");

    // 切换菜单速度，单位：ms
    const SLIDE_SPEED = 800;

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

    // 切换到注册界面
    function slideToRepoPage() {
        mainSwiper.slideTo(1, SLIDE_SPEED);
        repoSwiper.slideTo(2, SLIDE_SPEED);

        // 导航栏也要切换
        removeAndAddClass(navBtns[1]);
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
                repoSwiper.slideTo(1, SLIDE_SPEED);
            }

            if (isLogin === true) {
                repoSwiper.slideTo(2, SLIDE_SPEED);
            }
        });
    }

    // 给跳转到登陆界面的按钮绑定事件
    const loginBtns = $(".slideToLoginPageBtn");
    for (let i = 0; i < loginBtns.length; i++) {
        $(loginBtns[i]).click(slideToLoginPage);
    }

    // 给跳转到注册界面的按钮绑定事件
    const registerBtns = $(".slideToRegiterPageBtn");
    for (let i = 0; i < registerBtns.length; i++) {
        $(registerBtns[i]).click(slideToRegisterPage);
    }

    // 给切换按钮添加事件
    const switchBtns = $(".switchBtn");
    for (let i = 0; i < switchBtns.length; i++) {
        $(switchBtns[i]).click(switchShowOrUpdate);
    }

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
                    isLogin = true;

                    // 设置个人信息到页面上
                    putInfoOnSettingPage(resp.data);

                    // 修改首页按钮为跳转到笔记界面
                    $(".welcomePage .operation").hide();
                    $(".welcomePage .afterLogin").show();

                    // 跳转到首页
                    slideToWelcomePage();

                    // 登陆界面和注册界面要被隐藏
                    slideToRepoPage();

                    // 给跳转到笔记界面设置点击事件
                    $("#turnToRepo").click(slideToRepoPage);
                }
            }
        });
    });

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

    // 修改个人资料
    $("#saveBtn").click(function () {
        if (isLogin) {
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
});