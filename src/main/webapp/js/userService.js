/* 用户业务脚本 */
$(function () {
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
                url: "/user/register.do",
                type: "POST",
                data: form.serialize(),
                success: function (resp) {
                    if (resp.status === 0) {
                        alert(resp.msg);
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
            url: "/user/check_username.do",
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
});