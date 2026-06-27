接收消息 (IncomingMessage)
可能的类型如下：

friend 好友消息
字段名 类型 描述
message_scene string 固定值 friend，表示好友消息
peer_id int64 好友 QQ 号或群号
message_seq int64 消息序列号
sender_id int64 发送者 QQ 号
time int64 消息 Unix 时间戳（秒）
segments IncomingSegment[]    消息段列表
friend FriendEntity 好友信息
group 群消息
字段名 类型 描述
message_scene string 固定值 group，表示群消息
peer_id int64 好友 QQ 号或群号
message_seq int64 消息序列号
sender_id int64 发送者 QQ 号
time int64 消息 Unix 时间戳（秒）
segments IncomingSegment[]    消息段列表
group GroupEntity 群信息
group_member GroupMemberEntity 群成员信息
temp 临时会话消息
字段名 类型 描述
message_scene string 固定值 temp，表示临时会话消息
peer_id int64 好友 QQ 号或群号
message_seq int64 消息序列号
sender_id int64 发送者 QQ 号
time int64 消息 Unix 时间戳（秒）
segments IncomingSegment[]    消息段列表
group GroupEntity? 临时会话发送者的所在的群信息
