#NoteBookController

##根据笔记本获取他的笔记
- /show_notes_of_notebook.do	url
- (Integer page, String notebookId)	参数列表
- 页码，笔记本id   参数 
-  SUCCESS(0, "执行成功"),

		{
		"status": 0,
		"msg": "执行成功",
		"data": {
		"pageNum": 1,
		"pageSize": 8,
		"size": 2,
		"startRow": 1,
		"endRow": 2,
		"total": 2,
		"pages": 1,
		"list": [
		{
		"noteId": "48b6c5eb-cd99-4a62-a172-1eb96337e7f7",
		"notebookId": "19d4d1aa-671e-44a5-901e-53d58997105a",
		"userId": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"noteTitle": "112312312",
		"noteDetail": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"noteCreateTime": 1528698248000,
		"noteUpdateTime": 1528698248000
		},
		{
		"noteId": "d6244e4e-939a-49bf-bb33-f337d83a2197",
		"notebookId": "19d4d1aa-671e-44a5-901e-53d58997105a",
		"userId": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"noteTitle": "11231",
		"noteDetail": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"noteCreateTime": 1528698814000,
		"noteUpdateTime": 1528698814000
		}
		],
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 5,
		"navigatepageNums": [
		1
		],
		"navigateFirstPage": 1,
		"navigateLastPage": 1,
		"firstPage": 1,
		"lastPage": 1
		},
		"success": true
		}

##获取这个用户的所有笔记本
- /show_notebook_of_userId.do
- (Integer page, String userId)
- 页码，用户id
- SUCCESS(0, "执行成功"),

		{
		"status": 0,
		"msg": "执行成功",
		"data": {
		"pageNum": 1,
		"pageSize": 8,
		"size": 2,
		"startRow": 1,
		"endRow": 2,
		"total": 2,
		"pages": 1,
		"list": [
		{
		"notebookId": "19d4d1aa-671e-44a5-901e-53d58997105a",
		"userId": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"notebookName": "ä»å¤©æ¯ä¸ªå¥½æ¥å­",
		"notebookCreateTime": 1528697097000,
		"notebookUpdateTime": 1528697097000
		},
		{
		"notebookId": "dcc06f3f-0f1c-491d-b6e7-406b277737d2",
		"userId": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"notebookName": "ä»å¤©æ¯ä¸ªå¥½æ¥å­1",
		"notebookCreateTime": 1528697317000,
		"notebookUpdateTime": 1528697317000
		}
		],
		"prePage": 0,
		"nextPage": 0,
		"isFirstPage": true,
		"isLastPage": true,
		"hasPreviousPage": false,
		"hasNextPage": false,
		"navigatePages": 5,
		"navigatepageNums": [
		1
		],
		"navigateFirstPage": 1,
		"navigateLastPage": 1,
		"firstPage": 1,
		"lastPage": 1
		},
		"success": true
		}
##根据笔记id查找一个笔记
- /show_note.do
- (String noteId)
- 笔记id
- SUCCESS(0, "执行成功"),

		{
		"status": 0,
		"msg": "执行成功",
		"data": {
		"noteId": null,
		"notebookId": "19d4d1aa-671e-44a5-901e-53d58997105a",
		"userId": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"noteTitle": "112312312",
		"noteDetail": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"noteCreateTime": 1528698248000,
		"noteUpdateTime": 1528698248000
		},
		"success": true
		}

##根据笔记本id查找一个笔记本
- /show_notebook.do
- (String notebookId)
- 笔记本id
- - SUCCESS(0, "执行成功"),

		{
		"status": 0,
		"msg": "执行成功",
		"data": {
		"notebookId": "19d4d1aa-671e-44a5-901e-53d58997105a",
		"userId": "af8e9c46-a000-4f2e-aea1-f7bba5718b4a",
		"notebookName": "ä»å¤©æ¯ä¸ªå¥½æ¥å­",
		"notebookCreateTime": 1528697097000,
		"notebookUpdateTime": 1528697097000
		},
		"success": true
		}

##更新笔记本
- /update_notebook.do
- (NoteBook noteBook)
- userId notebookId notebookname
- SUCCESS(0, "执行成功"),

##更新笔记
- /update_note.do
- (Note note)
- userId notebookid noteid notetitle notedetail
- SUCCESS(0, "执行成功"),

##将一个笔记从一个笔记本移动到另外一个笔记本
- /move_note_to.do
- (Note note)
- userId,notebookId,noteId
- SUCCESS(0, "执行成功"),

##删除一个笔记
- /delete_note.do
- (Note note)
- userId  noteId
- SUCCESS(0, "执行成功"),

##删除笔记本，并且删除笔记本下的所有笔记
- /delete_notebook.do
- (NoteBook noteBook)
- userId notebookId
- SUCCESS(0, "执行成功"),

#File
##多个文件批量下载
- /multipleDownload.do
- (String[] fileNames, User user)
- fileNames  其实是file
- user   需要userId 和 username
- 下载的时候不需要提示

##删除文件
- /deleteFile.do
- String[] urls, User user)
- 一个url数组
- user需要username, userId
- FILE_DELETE_SUCCES(-7, "文件删除成功")

##根据文件的类型来列表
- /listFilesByType.do
- (User user, String type)
- user的userId  
- type是字符串，表示类别
- SUCCESS(0, "执行成功"),

		{
		"status": 0,
		"msg": "执行成功",
		"data": [
		{
		"userId": null,
		"url": "\\imp\\文件操作.txt",
		"type": null,
		"fileName": "文件操作.txt"
		}
		],
		"success": true
		}