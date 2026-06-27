事件 (Event)
字段名 类型 描述
event_type string 类型区分字段
time int64 事件 Unix 时间戳（秒）
self_id int64 机器人 QQ 号
data object 与 event_type 有关
data 在不同 event_type 下的具体类型如下：

bot_offline 机器人离线事件
字段名 类型 描述
reason string 下线原因
message_receive 消息接收事件
参见 IncomingMessage

message_recall 消息撤回事件
字段名 类型 描述
message_scene enum 消息场景，可能值：friend group temp
peer_id int64 好友 QQ 号或群号
message_seq int64 消息序列号
sender_id int64 被撤回的消息的发送者 QQ 号
operator_id int64 操作者 QQ 号
display_suffix string 撤回提示的后缀文本
peer_pin_change 会话置顶变更事件 v1.2
字段名 类型 描述
message_scene enum 发生改变的会话的消息场景，可能值：friend group temp
peer_id int64 发生改变的好友 QQ 号或群号
is_pinned boolean 是否被置顶, false 表示取消置顶
friend_request 好友请求事件
字段名 类型 描述
initiator_id int64 申请好友的用户 QQ 号
initiator_uid string 用户 UID
comment string 申请附加信息
via string 申请来源
group_join_request 入群请求事件
字段名 类型 描述
group_id int64 群号
notification_seq int64 请求对应的通知序列号
is_filtered boolean 请求是否被过滤（发起自风险账户）
initiator_id int64 申请入群的用户 QQ 号
comment string 申请附加信息
group_invited_join_request 群成员邀请他人入群请求事件
字段名 类型 描述
group_id int64 群号
notification_seq int64 请求对应的通知序列号
initiator_id int64 邀请者 QQ 号
target_user_id int64 被邀请者 QQ 号
group_invitation 他人邀请自身入群事件
字段名 类型 描述
group_id int64 群号
invitation_seq int64 邀请序列号
initiator_id int64 邀请者 QQ 号
source_group_idv1.2 int64? 来源群号，如果是通过 QQ 群邀请
friend_nudge 好友戳一戳事件
字段名 类型 描述
user_id int64 好友 QQ 号
is_self_send boolean 是否是自己发送的戳一戳
is_self_receive boolean 是否是自己接收的戳一戳
display_action string 戳一戳提示的动作文本
display_suffix string 戳一戳提示的后缀文本
display_action_img_url string 戳一戳提示的动作图片 URL，用于取代动作提示文本
friend_file_upload 好友文件上传事件
字段名 类型 描述
user_id int64 好友 QQ 号
file_id string 文件 ID
file_name string 文件名称
file_size int64 文件大小（字节）
file_hash string 文件的 TriSHA1 哈希值
is_self boolean 是否是自己发送的文件
group_admin_change 群管理员变更事件
字段名 类型 描述
group_id int64 群号
user_id int64 发生变更的用户 QQ 号
operator_idv1.1 int64 操作者 QQ 号
is_set boolean 是否被设置为管理员，false 表示被取消管理员
group_essence_message_change 群精华消息变更事件
字段名 类型 描述
group_id int64 群号
message_seq int64 发生变更的消息序列号
operator_idv1.1 int64 操作者 QQ 号
is_set boolean 是否被设置为精华，false 表示被取消精华
group_member_increase 群成员增加事件
字段名 类型 描述
group_id int64 群号
user_id int64 发生变更的用户 QQ 号
operator_id int64? 管理员 QQ 号，如果是管理员同意入群
invitor_id int64? 邀请者 QQ 号，如果是邀请入群
group_member_decrease 群成员减少事件
字段名 类型 描述
group_id int64 群号
user_id int64 发生变更的用户 QQ 号
operator_id int64? 管理员 QQ 号，如果是管理员踢出
group_name_change 群名称变更事件
字段名 类型 描述
group_id int64 群号
new_group_name string 新的群名称
operator_id int64 操作者 QQ 号
group_message_reaction 群消息表情回应事件
字段名 类型 描述
group_id int64 群号
user_id int64 发送回应者 QQ 号
message_seq int64 消息序列号
face_id string 表情 ID
reaction_typev1.2 enum 收到的回应类型，可能值：face emoji
is_add boolean 是否为添加，false 表示取消回应
group_mute 群禁言事件
字段名 类型 描述
group_id int64 群号
user_id int64 发生变更的用户 QQ 号
operator_id int64 操作者 QQ 号
duration int32 禁言时长（秒），为 0 表示取消禁言
group_whole_mute 群全体禁言事件
字段名 类型 描述
group_id int64 群号
operator_id int64 操作者 QQ 号
is_mute boolean 是否全员禁言，false 表示取消全员禁言
group_nudge 群戳一戳事件
字段名 类型 描述
group_id int64 群号
sender_id int64 发送者 QQ 号
receiver_id int64 接收者 QQ 号
display_action string 戳一戳提示的动作文本
display_suffix string 戳一戳提示的后缀文本
display_action_img_url string 戳一戳提示的动作图片 URL，用于取代动作提示文本
group_file_upload 群文件上传事件
字段名 类型 描述
group_id int64 群号
user_id int64 发送者 QQ 号
file_id string 文件 ID
file_name string 文件名称
file_size int64 文件大小（字节）
