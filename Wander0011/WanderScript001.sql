USE [Wander]
GO
/****** Object:  Table [dbo].[hibernate_sequence]    Script Date: 30-03-2019 17:40:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hibernate_sequence](
	[next_val] [bigint] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[mdc_user]    Script Date: 30-03-2019 17:40:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[mdc_user](
	[user_srlno] [int] NOT NULL,
	[user_id] [varchar](50) NULL,
	[user_password] [varchar](150) NULL,
	[role_id] [int] NULL,
	[active_flg] [int] NULL,
	[roleDescription] [varchar](50) NULL,
	[email] [varchar](50) NULL,
	[name] [varchar](50) NULL,
	[confirmPassword] [varchar](150) NULL,
 CONSTRAINT [PK_mdc_user] PRIMARY KEY CLUSTERED 
(
	[user_srlno] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[note]    Script Date: 30-03-2019 17:40:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[note](
	[noteId] [int] NOT NULL,
	[title] [varchar](150) NULL,
	[description] [varchar](150) NULL,
	[creation] [varchar](150) NULL,
	[updateTime] [datetime] NULL,
	[roleId] [int] NULL,
 CONSTRAINT [PK_note] PRIMARY KEY CLUSTERED 
(
	[noteId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tariff]    Script Date: 30-03-2019 17:40:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tariff](
	[tariff_id] [int] NOT NULL,
	[alarm_credit] [varchar](255) NULL,
	[convfactor] [varchar](255) NULL,
	[emr_credit] [varchar](255) NULL,
	[FixedCharges] [varchar](255) NULL,
	[last_date] [varchar](255) NULL,
	[tariff] [varchar](255) NULL,
	[tariffType] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[tariff_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[hibernate_sequence] ([next_val]) VALUES (3)
INSERT [dbo].[hibernate_sequence] ([next_val]) VALUES (3)
INSERT [dbo].[hibernate_sequence] ([next_val]) VALUES (3)
INSERT [dbo].[hibernate_sequence] ([next_val]) VALUES (3)
INSERT [dbo].[mdc_user] ([user_srlno], [user_id], [user_password], [role_id], [active_flg], [roleDescription], [email], [name], [confirmPassword]) VALUES (1, N'BKumar', N'cvp/LzpadrQT+2k0WDjyOQ==', 0, 1, NULL, N'bhrtsriram@gmail.com', N'Bharat', NULL)
INSERT [dbo].[note] ([noteId], [title], [description], [creation], [updateTime], [roleId]) VALUES (2, N'Java', N'Collections', N'GS Java', CAST(N'2019-03-30 17:35:38.000' AS DateTime), 1)
