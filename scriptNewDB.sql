USE [EventsManager]
GO
/****** Object:  Table [dbo].[tbl_account]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_account](
	[id_user] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](500) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[first_name] [nvarchar](50) NULL,
	[last_name] [nvarchar](50) NULL,
	[phone] [varchar](13) NULL,
	[enabled] [bit] NOT NULL,
	[create_date] [datetime] NULL,
	[update_time] [datetime] NULL,
 CONSTRAINT [PK_tblAccount] PRIMARY KEY CLUSTERED 
(
	[id_user] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_candidate]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_candidate](
	[id_candidate] [int] IDENTITY(1,1) NOT NULL,
	[national_id] [int] NULL,
	[account] [nvarchar](100) NULL,
	[name] [nvarchar](50) NULL,
	[id_school_code] [int] NOT NULL,
	[date_of_birth] [date] NULL,
	[gender] [varchar](6) NULL,
	[email] [nvarchar](100) NULL,
	[phone] [varchar](13) NULL,
	[facebook] [nvarchar](250) NULL,
	[university_garduation_date] [date] NULL,
	[fulltime_working_available_date] [date] NULL,
	[re_cer] [varchar](50) NULL,
	[rec_status] [varchar](50) NULL,
	[re_detail_note] [nvarchar](50) NULL,
	[cv_number] [int] NULL,
	[contract_type] [varchar](50) NULL,
	[id_sub_subject] [int] NOT NULL,
	[gpa] [float] NULL,
	[note] [nvarchar](500) NULL,
	[update_by] [nvarchar](50) NULL,
	[update_date] [date] NULL,
	[is_disable] [bit] NOT NULL,
 CONSTRAINT [PK_tblCandidate] PRIMARY KEY CLUSTERED 
(
	[id_candidate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_candidate_event]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_candidate_event](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_candidate] [int] NULL,
	[id_event] [int] NULL,
	[status] [varchar](10) NULL,
	[final_grade] [float] NULL,
	[completion_on_level] [varchar](10) NULL,
	[update_by] [nvarchar](50) NULL,
	[update_date] [date] NULL,
	[is_disable] [bit] NULL,
 CONSTRAINT [PK_tblCandidateEvent] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_cprogram]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_cprogram](
	[id_cprogram] [int] IDENTITY(1,1) NOT NULL,
	[program_name] [nvarchar](50) NOT NULL,
	[program_code] [varchar](10) NOT NULL,
	[time] [int] NULL,
	[is_disable] [bit] NULL,
 CONSTRAINT [PK_tblCProgram] PRIMARY KEY CLUSTERED 
(
	[id_cprogram] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_event]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_event](
	[id_event] [int] IDENTITY(1,1) NOT NULL,
	[id_school_code] [int] NOT NULL,
	[id_cprogram] [int] NOT NULL,
	[course_code] [varchar](50) NOT NULL,
	[subject_type] [varchar](50) NOT NULL,
	[format_type] [varchar](50) NULL,
	[id_sub_subject] [int] NOT NULL,
	[planned_start_date] [date] NOT NULL,
	[planned_end_date] [date] NOT NULL,
	[planned_expense] [nvarchar](50) NULL,
	[budget_code] [nvarchar](50) NULL,
	[actual_start_date] [date] NULL,
	[actual_end_date] [date] NULL,
	[actual_expense] [nvarchar](50) NULL,
	[training_feedback] [nvarchar](max) NULL,
	[training_feedback_content] [nvarchar](max) NULL,
	[training_feedback_teacher] [nvarchar](max) NULL,
	[training_feedback_organization] [nvarchar](max) NULL,
	[update_by] [nvarchar](50) NULL,
	[update_date] [date] NULL,
	[note] [nvarchar](max) NULL,
	[is_disable] [bit] NOT NULL CONSTRAINT [DF_tbl_event_is_disable]  DEFAULT ((0)),
 CONSTRAINT [PK_tblEvent] PRIMARY KEY CLUSTERED 
(
	[id_event] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_role]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_role](
	[user_role_id] [int] IDENTITY(1,1) NOT NULL,
	[userid] [int] NULL,
	[role_name] [nvarchar](50) NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[user_role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tbl_school_code]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tbl_school_code](
	[id_school_code] [int] IDENTITY(1,1) NOT NULL,
	[site] [varchar](10) NOT NULL,
	[university_name] [nvarchar](100) NOT NULL,
	[faculty_name] [nvarchar](50) NOT NULL,
	[university_code] [varchar](50) NOT NULL,
	[hot_key] [int] NOT NULL,
	[faculty_code] [varchar](10) NOT NULL,
	[rank] [int] NULL,
	[cooperation_start] [date] NULL,
	[note] [nvarchar](max) NULL,
	[is_disable] [bit] NULL,
 CONSTRAINT [PK_tblSchoolCode] PRIMARY KEY CLUSTERED 
(
	[id_school_code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tbl_sub_subject_type]    Script Date: 06/8/2019 3:17:02 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_sub_subject_type](
	[id_sub_subject] [int] IDENTITY(1,1) NOT NULL,
	[skill] [nvarchar](50) NOT NULL,
	[is_disable] [bit] NULL,
 CONSTRAINT [PK_tblSubSubjectType] PRIMARY KEY CLUSTERED 
(
	[id_sub_subject] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[tbl_account] ON 

INSERT [dbo].[tbl_account] ([id_user], [username], [password], [email], [first_name], [last_name], [phone], [enabled], [create_date], [update_time]) VALUES (1, N'baobao1', N'$2a$10$0sC4qlLouANjrkNVT/CEReTn5tI.hRmTTUc7nCsPuHqqfDBsLeF2y', N'baobao1@gmail.com', N'Hồ', N'Quang Bảo', N'0987646363', 1, NULL, NULL)
INSERT [dbo].[tbl_account] ([id_user], [username], [password], [email], [first_name], [last_name], [phone], [enabled], [create_date], [update_time]) VALUES (2, N'cuongcuong2', N'$2a$10$0sC4qlLouANjrkNVT/CEReTn5tI.hRmTTUc7nCsPuHqqfDBsLeF2y', N'cuonghx@gmail.com', N'Hồ', N'Xuân Cường', N'0983456789', 1, NULL, NULL)
INSERT [dbo].[tbl_account] ([id_user], [username], [password], [email], [first_name], [last_name], [phone], [enabled], [create_date], [update_time]) VALUES (3, N'hieunho', N'$2a$10$0sC4qlLouANjrkNVT/CEReTn5tI.hRmTTUc7nCsPuHqqfDBsLeF2y', N'hieu@gmail.com', N'Phạm', N' Đức Hiếu', N'0984663632', 1, NULL, NULL)
INSERT [dbo].[tbl_account] ([id_user], [username], [password], [email], [first_name], [last_name], [phone], [enabled], [create_date], [update_time]) VALUES (4, N'haihlt', N'$2a$10$0sC4qlLouANjrkNVT/CEReTn5tI.hRmTTUc7nCsPuHqqfDBsLeF2y', N'haihlt@gmail.com', N'Hoàng', N'Lưu Thanh Hải', N'0985453533', 1, NULL, NULL)
SET IDENTITY_INSERT [dbo].[tbl_account] OFF
SET IDENTITY_INSERT [dbo].[tbl_candidate] ON 

INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (1, 0, N'datnddse62946@fpt.edu.vn', N'Nguyễn Đình Đức Đạt', 27, CAST(N'1998-03-16' AS Date), N'Male', N'datnddse62946@fpt.edu.vn', N'0906331677', NULL, NULL, NULL, NULL, N'Transfer', N'16/1/2019', 0, N'On job training Contract', 8, 10, NULL, N'TrinhPTT', CAST(N'2019-01-02' AS Date), 0)
INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (2, 0, N'hienvdse63056@fpt.edu.vn', N'Vũ Đức Hiển', 27, CAST(N'1998-04-16' AS Date), N'Male', N'hienvdse63056@fpt.edu.vn', N'0349871752', NULL, NULL, NULL, NULL, N'Transfer', N'23/1/2019', 1, N'On job training Contract', 8, 10, NULL, N'TrucLHT', CAST(N'2019-01-18' AS Date), 0)
INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (3, 0, N'thotdse63073@fpt.edu.vn', N'Trần Đức Thọ', 27, CAST(N'1998-10-15' AS Date), N'Male', N'thotdse63073@fpt.edu.vn', N'0947857301', NULL, NULL, NULL, NULL, N'Transfer', N'16/1/2019', 2, N'On job training Contract', 8, 10, NULL, N'TrucLHT', CAST(N'2019-01-18' AS Date), 0)
INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (4, 0, N'duyntse63133@fpt.edu.vn', N'Nguyễn Triệu Duy', 27, CAST(N'1998-05-01' AS Date), N'Male', N'duyntse63133@fpt.edu.vn', N'0966086102', NULL, NULL, NULL, NULL, N'Transfer', N'7/1/2019', 3, N'On job training Contract', 8, 10, NULL, N'DanPL', CAST(N'2019-01-02' AS Date), 0)
INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (8, 0, N'minhquy11121997@gmail.com', N'Bùi Minh Quí', 14, CAST(N'1997-03-05' AS Date), N'Male', N'minhquy11121997@gmail.com', N'0933483232', NULL, NULL, NULL, NULL, N'Transfer', N'7/1/2019', 4, N'On job training Contract', 1, 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (9, 0, N'vietthanh090198@gmail.com', N'Nguyễn Việt Thanh', 14, CAST(N'1997-03-05' AS Date), N'Male', N'vietthanh090198@gmail.com', N'0121456782', NULL, NULL, NULL, NULL, N'Transfer', N'7/1/2019', 5, N'On job training Contract', 1, 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (11, 0, N'namnguyen1208198@gmail.com', N'Nguyễn Võ Hoài Nam', 14, CAST(N'1997-03-05' AS Date), N'Male', N'namnguyen1208198@gmail.com', N'0984733131', NULL, NULL, NULL, NULL, N'Transfer', N'7/1/2019', 6, N'On job training Contract', 1, 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
INSERT [dbo].[tbl_candidate] ([id_candidate], [national_id], [account], [name], [id_school_code], [date_of_birth], [gender], [email], [phone], [facebook], [university_garduation_date], [fulltime_working_available_date], [re_cer], [rec_status], [re_detail_note], [cv_number], [contract_type], [id_sub_subject], [gpa], [note], [update_by], [update_date], [is_disable]) VALUES (12, 0, N'thanhlonghunter@gmail.com', N'Phan Thành Long', 14, CAST(N'1998-01-10' AS Date), N'Male', N'thanhlonghunter@gmail.com', N'0987546331', NULL, NULL, NULL, NULL, N'Transfer', N'7/1/2019', 7, N'On job training Contract', 1, 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
SET IDENTITY_INSERT [dbo].[tbl_candidate] OFF
SET IDENTITY_INSERT [dbo].[tbl_candidate_event] ON 

INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (1, 1, 8, N'Active', 10, NULL, N'TrinhPTT', CAST(N'2019-01-02' AS Date), 0)
INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (2, 2, 8, N'Active', 10, NULL, N'TrucLHT', CAST(N'2019-01-18' AS Date), 0)
INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (3, 3, 8, N'Active', 10, NULL, N'TrucLHT', CAST(N'2019-01-18' AS Date), 0)
INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (4, 4, 8, N'Active', 10, NULL, N'DanPL', CAST(N'2019-01-02' AS Date), 0)
INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (5, 8, 4012, N'Passed', 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (6, 9, 4012, N'Drop-Out', 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (7, 11, 4012, N'Cancel', 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
INSERT [dbo].[tbl_candidate_event] ([id], [id_candidate], [id_event], [status], [final_grade], [completion_on_level], [update_by], [update_date], [is_disable]) VALUES (8, 12, 4012, N'Failed', 0, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), 0)
SET IDENTITY_INSERT [dbo].[tbl_candidate_event] OFF
SET IDENTITY_INSERT [dbo].[tbl_cprogram] ON 

INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (1, N'Global Software Talent', N'GST', 360, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (2, N'THESIS', N'THESIS', 0, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (3, N'GST LITE', N'LITE', 320, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (4, N'INTERNSHIP', N'INTE', 320, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (5, N'SEMINAR', N'SEMI', 4, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (6, N'JOBFAIR', N'JOB', 0, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (8, N'FSOFT TOUR', N'FTOUR', 3, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (9, N'Contest Sponsor', N'CSR', 0, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (10, N'REC', N'REC', 0, 0)
INSERT [dbo].[tbl_cprogram] ([id_cprogram], [program_name], [program_code], [time], [is_disable]) VALUES (11, N'ONLINE', N'ONLINE', 0, 0)
SET IDENTITY_INSERT [dbo].[tbl_cprogram] OFF
SET IDENTITY_INSERT [dbo].[tbl_event] ON 

INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (1, 16, 4, N'HCM.IUH_INTE_JAVA_HCM19_001', N'IT Technical', N'Blended', 3, CAST(N'2019-01-02' AS Date), CAST(N'2019-03-15' AS Date), N'100', N'100', CAST(N'2019-01-02' AS Date), CAST(N'2019-03-15' AS Date), NULL, NULL, NULL, NULL, NULL, N'TraDT', CAST(N'2018-12-19' AS Date), NULL, 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (7, 16, 4, N'HCM.IUH_INTE_NET_HCM19_002', N'IT Technical', N'Blended', 1, CAST(N'2019-01-02' AS Date), CAST(N'2019-03-15' AS Date), N'200', N'200', CAST(N'2019-01-02' AS Date), CAST(N'2019-03-15' AS Date), NULL, NULL, NULL, NULL, NULL, N'TraDT', CAST(N'2018-12-19' AS Date), NULL, 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (8, 27, 4, N'HCM.FU_INTE_ALL_HCM19_003', N'IT Technical', N'Blended', 8, CAST(N'2019-01-07' AS Date), CAST(N'2019-04-15' AS Date), N'100', N'100', CAST(N'2019-01-07' AS Date), CAST(N'2019-04-15' AS Date), NULL, NULL, NULL, NULL, NULL, N'TraDT', CAST(N'2018-12-20' AS Date), NULL, 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (3012, 14, 5, N'HCM.TDT_SEMI_IT Technical_HCM19_004', N'IT Technical', N'Blended', 8, CAST(N'2019-08-14' AS Date), CAST(N'2019-11-30' AS Date), N'100', N'100', CAST(N'2019-08-14' AS Date), CAST(N'2019-11-30' AS Date), NULL, NULL, NULL, NULL, NULL, NULL, CAST(N'2019-08-05' AS Date), N'aaaaaaaaaaaaaaaaaaaaaaaaa', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (3013, 25, 2, N'HCM.LHU_THESIS_IOS_HCM19_005', N'IT Technical', N'Blended', 4, CAST(N'2019-08-14' AS Date), CAST(N'2019-08-20' AS Date), N'100', N'100', CAST(N'2019-08-14' AS Date), CAST(N'2019-08-20' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'fyfdgkhhjvjh', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (3014, 5, 3, N'HCM.HCMUT_LITE_TEST_HCM19_006', N'IT Technical', N'Blended', 2, CAST(N'2019-08-22' AS Date), CAST(N'2019-08-23' AS Date), N'100', N'100', CAST(N'2019-08-22' AS Date), CAST(N'2019-08-23' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'aaaaaaaaaaaaaaaaaaaaa', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (3015, 8, 2, N'HCM.HCMUP_THESIS_TEST_HCM19_007', N'IT Technical', N'Blended', 2, CAST(N'2019-08-29' AS Date), CAST(N'2019-09-15' AS Date), N'100', N'100', CAST(N'2019-08-29' AS Date), CAST(N'2019-09-15' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'dddddddddddddddddd', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4012, 14, 8, N'HCM.TDT_FTOUR_NET_HCM19_008', N'IT Technical', N'Blended', 1, CAST(N'2019-08-24' AS Date), CAST(N'2019-08-25' AS Date), N'100', N'100', CAST(N'2019-08-24' AS Date), CAST(N'2019-08-25' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'dddddddddddddddd', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4013, 19, 3, N'HCM.NTT_LITE_EMBED_HCM19_009', N'IT Technical', N'Blended', 7, CAST(N'2019-08-14' AS Date), CAST(N'2019-08-23' AS Date), N'200', N'200', CAST(N'2019-08-14' AS Date), CAST(N'2019-08-23' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'daaaaaaaaaaaaaaaaaaaa', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4014, 26, 6, N'HCM.TDMU_JOB_EMBED_HCM19_010', N'IT Technical', N'Blended', 7, CAST(N'2019-08-15' AS Date), CAST(N'2019-08-19' AS Date), N'100', N'100', CAST(N'2019-08-15' AS Date), CAST(N'2019-08-19' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'aaaaaaaaaaaaaaaaaaaaaaaaaaa', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4015, 24, 2, N'HCM.HUFLIT_THESIS_JAVA_HCM19_011', N'IT Technical', N'Blended', 3, CAST(N'2019-08-23' AS Date), CAST(N'2019-08-31' AS Date), N'100', N'100', CAST(N'2019-08-23' AS Date), CAST(N'2019-08-31' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'fffffffffffffffffffffffffff', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4016, 23, 4, N'HCM.UIT_INTE_IOS_HCM19_012', N'IT Technical', N'Blended', 4, CAST(N'2019-08-14' AS Date), CAST(N'2019-08-30' AS Date), N'200', N'200', CAST(N'2019-08-14' AS Date), CAST(N'2019-08-30' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'ddddddddddddddeeeeeeeeeeeee', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4017, 1, 4, N'HCM.UAF_INTE_ALL_HCM19_013', N'IT Technical', N'Blended', 8, CAST(N'2019-08-13' AS Date), CAST(N'2019-11-14' AS Date), N'100', N'100', CAST(N'2019-08-13' AS Date), CAST(N'2019-11-14' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'guycygcujhyuucyg', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4018, 9, 11, N'HCM.CTU_ONLINE_TEST_HCM19_014', N'IT Technical', N'Blended', 2, CAST(N'2019-11-12' AS Date), CAST(N'2020-02-01' AS Date), N'100', N'100', CAST(N'2019-11-12' AS Date), CAST(N'2020-02-01' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-05' AS Date), N'Thay giao ba', 0)
INSERT [dbo].[tbl_event] ([id_event], [id_school_code], [id_cprogram], [course_code], [subject_type], [format_type], [id_sub_subject], [planned_start_date], [planned_end_date], [planned_expense], [budget_code], [actual_start_date], [actual_end_date], [actual_expense], [training_feedback], [training_feedback_content], [training_feedback_teacher], [training_feedback_organization], [update_by], [update_date], [note], [is_disable]) VALUES (4020, 27, 4, N'HCM.FU_INTE_JAVA_HCM19_015', N'IT Technical', N'Blended', 3, CAST(N'2019-08-13' AS Date), CAST(N'2019-10-14' AS Date), N'100', N'100', CAST(N'2019-08-13' AS Date), CAST(N'2019-10-14' AS Date), NULL, NULL, NULL, NULL, NULL, N'baobao1', CAST(N'2019-08-06' AS Date), N'gdsgdfgdsfgdsf', 0)
SET IDENTITY_INSERT [dbo].[tbl_event] OFF
SET IDENTITY_INSERT [dbo].[tbl_role] ON 

INSERT [dbo].[tbl_role] ([user_role_id], [userid], [role_name]) VALUES (1, 1, N'ROLE_ADMIN')
INSERT [dbo].[tbl_role] ([user_role_id], [userid], [role_name]) VALUES (2, 2, N'ROLE_USER')
INSERT [dbo].[tbl_role] ([user_role_id], [userid], [role_name]) VALUES (3, 3, N'ROLE_ADMIN')
INSERT [dbo].[tbl_role] ([user_role_id], [userid], [role_name]) VALUES (4, 3, N'ROLE_USER')
INSERT [dbo].[tbl_role] ([user_role_id], [userid], [role_name]) VALUES (5, 4, N'ROLE_ADMIN')
INSERT [dbo].[tbl_role] ([user_role_id], [userid], [role_name]) VALUES (6, 4, N'ROLE_USER')
SET IDENTITY_INSERT [dbo].[tbl_role] OFF
SET IDENTITY_INSERT [dbo].[tbl_school_code] ON 

INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (1, N'HCM', N'ĐH Nông Lâm TP HCM', N'Công nghệ thông tin', N'HCM.UAF', 538, N'ICT', 1, CAST(N'2014-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (2, N'HCM', N'ĐH Sư phạm Kỹ thuật HCM ( Điện điện tử)', N'Điện điện tử', N'HCM.HCMUTE', 320, N'SET', 2, CAST(N'2016-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (4, N'HCM', N'ĐH Sư phạm Kỹ thuật HCM (CNTT)', N'Công nghệ thông tin', N'HCM.HCMUTE', 320, N'ICT', 2, CAST(N'2016-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (5, N'HCM', N'ĐH Bách khoa HCM', N'Khoa Học và Kỹ Thuật Máy Tính', N'HCM.HCMUT', 305, N'ICT', 3, CAST(N'2014-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (6, N'HCM', N'ĐH QG HCM - ĐH KHTN ( Toán & Tin )', N'Toán & Tin Học', N'HCM.HCMUS', 307, N'MAT', 2, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (7, N'HCM', N'ĐH QG HCM - ĐH KHTN ( CNTT)', N'Công nghệ thông tin', N'HCM.HCMUS', 307, N'ICT', 3, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (8, N'HCM', N'ĐH Sư phạm HCM', N'Công nghệ thông tin', N'HCM.HCMUP', 321, N'ICT', 1, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (9, N'HCM', N'ĐH Cần Thơ', N'Công nghệ thông tin', N'HCM.CTU', 532, N'ICT', 2, CAST(N'2014-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (11, N'HCM', N'Cao Đẳng thực hành Fpoly', N'Công nghệ thông tin', N'HCM.POLY', 0, N'ICT', 1, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (14, N'HCM', N'ĐH Tôn Đức Thắng', N'Công nghệ thông tin', N'HCM.TDT', 548, N'ICT', 2, CAST(N'2014-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (16, N'HCM', N'ĐH Công Nghiệp TP HCM', N'Công nghệ thông tin', N'HCM.IUH', 284, N'ICT', 2, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (17, N'HCM', N'ĐH Công Nghệ TP HCM', N'Công nghệ thông tin', N'HCM.HUTECH', 530, N'ICT', 2, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (19, N'HCM', N'ĐH Nguyễn Tất Thành', N'Công nghệ thông tin', N'HCM.NTT', 540, N'ICT', 1, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (20, N'HCM', N'ĐH - Học Viện Bưu Chính Viễn Thông HCM', N'Công nghệ thông tin', N'HCM.PTIT', 333, N'ICT', 1, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (21, N'HCM', N'Cao Đẳng Thủ Đức', N'Công nghệ thông tin', N'HCM.TDC', 0, N'ICT', 1, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (22, N'HCM', N'ĐH Công Nghệ Thông Tin, ĐH Quốc gia TP HCM (KTMT)', N'Kỹ thuật máy tính', N'HCM.UIT', 304, N'ICT-CE', 3, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (23, N'HCM', N'ĐH Công Nghệ Thông Tin, ĐH Quốc gia TP HCM (KTPM)', N'Kĩ thuật phần mềm', N'HCM.UIT', 304, N'ICT-SE', 3, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (24, N'HCM', N'ĐH Ngoại Ngữ - Tin Học TP HCM', N'Công nghệ thông tin', N'HCM.HUFLIT', 539, N'ICT', 1, CAST(N'2016-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (25, N'HCM', N'ĐH Lạc Hồng', N'Công nghệ thông tin', N'HCM.LHU', 536, N'ICT', 1, CAST(N'2015-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (26, N'HCM', N'Đại học Thủ Dầu Một', N'Công nghệ thông tin', N'HCM.TDMU', 458, N'ICT', 1, CAST(N'2016-04-01' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (27, N'HCM', N'ĐH FPT', N'Công nghệ thông tin', N'HCM.FU', 47, N'ICT', 3, CAST(N'2016-01-16' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (28, N'HCM', N'ĐH Mở HCM', N'Công nghệ thông tin', N'HCM.OU', 298, N'ICT', 1, CAST(N'2018-12-20' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (29, N'HCM', N'ĐH Giao thông vận tải HCM', N'Công nghệ thông tin', N'HCM.UCT', 288, N'ICT', 1, CAST(N'2018-12-20' AS Date), NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (30, N'HCM', N'CĐ Kỹ thuật Cao Thắng', N'Công nghệ thông tin', N'HCM.CTTC', 267, N'ICT', 3, NULL, NULL, 0)
INSERT [dbo].[tbl_school_code] ([id_school_code], [site], [university_name], [faculty_name], [university_code], [hot_key], [faculty_code], [rank], [cooperation_start], [note], [is_disable]) VALUES (31, N'HCM', N'ĐH Sài Gòn', N'Công nghệ thông tin', N'HCM.SGU', 318, N'ICT', 3, NULL, NULL, 0)
SET IDENTITY_INSERT [dbo].[tbl_school_code] OFF
SET IDENTITY_INSERT [dbo].[tbl_sub_subject_type] ON 

INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (1, N'NET', 0)
INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (2, N'TEST', 0)
INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (3, N'JAVA', 0)
INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (4, N'IOS', 0)
INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (5, N'Đề Tài Tốt Nghiệp', 0)
INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (6, N'AI&ML', 0)
INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (7, N'EMBED', 0)
INSERT [dbo].[tbl_sub_subject_type] ([id_sub_subject], [skill], [is_disable]) VALUES (8, N'ALL', 0)
SET IDENTITY_INSERT [dbo].[tbl_sub_subject_type] OFF
ALTER TABLE [dbo].[tbl_candidate]  WITH CHECK ADD  CONSTRAINT [FK_tblCandidate_tblSchoolCode] FOREIGN KEY([id_school_code])
REFERENCES [dbo].[tbl_school_code] ([id_school_code])
GO
ALTER TABLE [dbo].[tbl_candidate] CHECK CONSTRAINT [FK_tblCandidate_tblSchoolCode]
GO
ALTER TABLE [dbo].[tbl_candidate]  WITH CHECK ADD  CONSTRAINT [FK_tblCandidate_tblSubSubjectType] FOREIGN KEY([id_sub_subject])
REFERENCES [dbo].[tbl_sub_subject_type] ([id_sub_subject])
GO
ALTER TABLE [dbo].[tbl_candidate] CHECK CONSTRAINT [FK_tblCandidate_tblSubSubjectType]
GO
ALTER TABLE [dbo].[tbl_candidate_event]  WITH CHECK ADD  CONSTRAINT [FK_tblCandidateEvent_tblCandidate] FOREIGN KEY([id_candidate])
REFERENCES [dbo].[tbl_candidate] ([id_candidate])
GO
ALTER TABLE [dbo].[tbl_candidate_event] CHECK CONSTRAINT [FK_tblCandidateEvent_tblCandidate]
GO
ALTER TABLE [dbo].[tbl_candidate_event]  WITH CHECK ADD  CONSTRAINT [FK_tblCandidateEvent_tblEvent] FOREIGN KEY([id_event])
REFERENCES [dbo].[tbl_event] ([id_event])
GO
ALTER TABLE [dbo].[tbl_candidate_event] CHECK CONSTRAINT [FK_tblCandidateEvent_tblEvent]
GO
ALTER TABLE [dbo].[tbl_event]  WITH CHECK ADD  CONSTRAINT [FK_tblEvent_tblCProgram] FOREIGN KEY([id_cprogram])
REFERENCES [dbo].[tbl_cprogram] ([id_cprogram])
GO
ALTER TABLE [dbo].[tbl_event] CHECK CONSTRAINT [FK_tblEvent_tblCProgram]
GO
ALTER TABLE [dbo].[tbl_event]  WITH CHECK ADD  CONSTRAINT [FK_tblEvent_tblSchoolCode] FOREIGN KEY([id_school_code])
REFERENCES [dbo].[tbl_school_code] ([id_school_code])
GO
ALTER TABLE [dbo].[tbl_event] CHECK CONSTRAINT [FK_tblEvent_tblSchoolCode]
GO
ALTER TABLE [dbo].[tbl_event]  WITH CHECK ADD  CONSTRAINT [FK_tblEvent_tblSubSubjectType] FOREIGN KEY([id_sub_subject])
REFERENCES [dbo].[tbl_sub_subject_type] ([id_sub_subject])
GO
ALTER TABLE [dbo].[tbl_event] CHECK CONSTRAINT [FK_tblEvent_tblSubSubjectType]
GO
ALTER TABLE [dbo].[tbl_role]  WITH CHECK ADD  CONSTRAINT [FK_tblRole_tblAccount] FOREIGN KEY([userid])
REFERENCES [dbo].[tbl_account] ([id_user])
GO
ALTER TABLE [dbo].[tbl_role] CHECK CONSTRAINT [FK_tblRole_tblAccount]
GO
