####author: imp


#####1.文件上传<br>
url: /upFile.do<br>
参数：file （需要上传一个文件）

    success:
         {
         "status": 0,
         "msg": "文件上传成功",
         "data": null,
         "success": true
         }
 
 #####2.文件重命名
 url: /renameFile.do<br>
 参数： partUri（每个文件上带有的uri属性）<br>  fileName（文件名）
 
    success:
    {
    "status": 0,
    "msg": "文件重命名成功",
    "data": null,
    "success": true
    }
    
  #####3.获得全部文件
  url:/getFileList.do<br>
  参数：fileName（文件夹名）<br>  back（是否点击了返回按钮）
  
    success:
    {
    "status": 0,
    "msg": "获取文件列表成功",
    "data": [
    {
    "userId": null,
    "url": "\\imp\\文件操作.txt",
    "type": "文本",
    "fileName": "文件操作.txt"
    }
    ],
    "success": true
    }
  
   #####4.新建文件夹
   url:/newFolder.do<br>
   参数：folderName（文件夹名）<br>  
   
     success:
     {
     "status": 0,
     "msg": "文件重命名成功",
     "data": null,
     "success": true
     }
  
   

    
