// 一加载完页面就执行脚本
// 大界面的切换器
const mainSwiper = new Swiper('#mainSwiper', {
    allowTouchMove: false, // 只能通过点击导航栏来切换，不允许直接切换
});

// 笔记界面的切换器
const repoSwiper = new Swiper('#repoSwiper', {
    allowTouchMove: false, // 只能通过点击导航栏来切换，不允许直接切换
    direction: 'vertical',
});

$(function () {
    const SLIDE_SPEED = 800; // 单位：ms

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

    // 点击导航栏样式切换
    const navBtns = $(".navBtn li");
    for (let i = 0; i < navBtns.length; i++) {
        $(navBtns[i]).click(function () {
            // 清除原来的样式，然后给当前元素加上样式
            removeAndAddClass(this);

            // 切换到指定页面
            mainSwiper.slideTo(i, SLIDE_SPEED);

            if (i === 1) {
                repoSwiper.slideTo(1, SLIDE_SPEED);
            }
        });
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
});