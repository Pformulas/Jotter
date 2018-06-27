/**
 * Created by qiangxl on 2018/6/14 0014.
 */
/*侧边栏和主体内容的tab切换*/
$(function () {
  var count = 0;
  var id_list = new Array();
  $('#_menu_list li:eq("1")').addClass('cur').show();
  $('#_main_body div.mod-list-group').hide();
  $('#_main_body div.mod-list-group:eq("1")').show();

  $('#_menu_list li').click(function () {
    $('#_menu_list li').removeClass("cur");
    $(this).addClass('cur');
    $('#_main_body div.mod-list-group').hide();
    var activeTab = $(this).find('a').attr('data-type');
    $("#_main_body [data-type=" + activeTab + "]").show();
  });
  // 点击打勾，显示下载，分享，删除，更多的button,count用来表示当最后一个勾取消时，button消失
  $('.item-icon input').click(function () {
    if ($(this).parent().parent().attr('class') == 'list-group-item act') {
      count--;
      if(count == 0){
        $('div.ui.icon.basic.buttons').css({
          'display':'none',
        });
      }
      $(this).parent().parent().removeClass('act');
    } else {
      count++;
      $(this).parent().parent().addClass('act');
      $('div.ui.icon.basic.buttons').css({
        'display':'inline-flex',
        'margin-left': '10px',
      });
      
      console.log($(this).attr('name'));
    }
  });

  //跳转
  $('.item-tit a').click(function () {
    //title用数据库中的filename
    //到时候就用这个，title来获取，是哪个文件，然后用来跳转到指定的页面 
    var $title1 = $(this).attr('title');
    //$(this).attr('href',"https://www.weiyun.com/disk");
  });
  fileUpload =  function () {
    $('#File').click();
  };
  
  //新建
  $('#plus').click(function () {
    $('#plus_modal').modal({
      keyboardShortcuts:false,
      closable:false,
    }).modal('show');
  });
  $('#plus_ensure').click(function () {
    //如果输入框为空，说明没有输入文件夹的名字。就不让它继续
    if($('input[name="fileName"]').val() == ""){
      $('input[name="fileName"]').attr('placeholder',"文件名不能为空").css({
        'width':'200px!important',
        }
      );
      console.log($('input[name="fileName"]'));
      //说明输入了文件夹的名字
    }else{
      $('#plus_modal').modal('hide');
    }
  });
  $('#plus_console').click(function () {
    $('#plus_modal').modal('hide');
  });

  //删除
  $('#delete').click(function () {
    if(count >= 2){
      $('#delete_modal .content .ui.header').text("你确定要删除这些文件(夹)?");
    }
    $('#delete_modal').modal({
      keyboardShortcuts:false,
      closable:false,
    }).modal('show');
  });
  $('#delete_ensure').click(function () {
    $('#delete_modal').modal('hide');
  });
  $('#delete_modal').click(function () {
    $('#delete_console').click(function () {
      $('#delete_modal').modal('hide');
    })
  })
});

