CREATE DATABASE SkyPhone;
go
USE SkyPhone;
go

CREATE TABLE TaiKhoan(
	[email] [varchar](100) PRIMARY KEY NOT NULL,
	[password][varchar](40) NOT NULL,
	[ho_ten] [nvarchar](20) NOT NULL,
	[sdt] [varchar](11) NOT NULL UNIQUE,
	[cmnd] [varchar](12) NOT NULL UNIQUE,
	[vai_tro] [int] NOT NULL,
	[hinh] [nvarchar](50) NOT NULL,
)

CREATE TABLE NhanHang(
	[id] [int] PRIMARY KEY IDENTITY NOT NULL,
	[ten_nhan_hang] [nvarchar](100) NOT NULL
)

CREATE TABLE DienThoai(
	[id_dt] [varchar](255) PRIMARY KEY NOT NULL DEFAULT(NEWID()),
	[ten_dt] [nvarchar](50) NOT NULL,
	[dung_luong] [varchar](20) NOT NULL,
	[mau] [nvarchar](20) NOT NULL,
	[tra_gop] [nvarchar](20) NULL,
	[nha_sx] [nvarchar](20) NULL,
	[gia] [float] NOT NULL,
	[mo_ta] [nvarchar](MAX) NULL,
	[bao_hanh] [nvarchar](MAX) NULL,
	[hoat_dong] [bit] NULL DEFAULT ((0)),
	[so_luong][int] NOT NULL,
	[id_nhan_hang] [int] NOT NULL FOREIGN KEY REFERENCES NhanHang (id)
)


CREATE TABLE HoaDon(
	[id_hd] [varchar](255) PRIMARY KEY NOT NULL DEFAULT(NEWID()),
	[nguoi_thanh_toan] [nvarchar](20) NULL,
	[so_luong_don] [int] NOT NULL,
	[tong_gia] [float] NOT NULL,
	[dia_chi_gui] [nvarchar](MAX) NOT NULL,
	[ten_nguoi_nhan] [nvarchar](100) NOT NULL,
	[sdt_nguoi_nhan] [nvarchar](11) NOT NULL,
	[dia_chi_nhan] [nvarchar](MAX) NOT NULL,
	[ngay_tao_don] [date] NOT NULL DEFAULT(GETDATE()),
	[tinh_trang] [int] NOT NULL
)

CREATE TABLE HDChiTiet(
	[stt] [int] PRIMARY KEY NOT NULL IDENTITY,
	[so_luong_don] [int] NOT NULL,
	[tong_gia_dct] [money] NOT NULL,
	[id_hd] [varchar](255) NOT NULL FOREIGN KEY REFERENCES HoaDon (id_hd),
	[id_dt] [varchar](255) NOT NULL FOREIGN KEY REFERENCES DienThoai (id_dt)
)
CREATE TABLE DanhGia(
	[stt] [int] PRIMARY KEY NOT NULL IDENTITY,
	[sdt] [nvarchar](11) NOT NULL,
	[noi_dung] [nvarchar](MAX) NOT NULL,
	[trang_thai] [bit] default(0),
	[id_dt] [varchar](255) NOT NULL FOREIGN KEY REFERENCES DienThoai (id_dt)
)
go
------------------------------------------------------------------Create proc
create proc proc_report1
as
begin
	declare @value table(
		sdt_nguoi_nhan varchar(11),
		ten_nguoi_nhan nvarchar(255),
		tong_gia float,
		luot_mua int,
		tong_so_don_hang_da_mua int
	)
	declare @temp table(
		STT int IDENTITY,
		sdt_nguoi_nhan varchar(11)
	)
	insert into @temp Select DISTINCT sdt_nguoi_nhan from HoaDon
	declare @i int = 1
	declare @tempCount int = (Select COUNT(*) from @Temp)
	while @i < @tempCount + 1
	begin
		declare @sdt_nguoi_nhan varchar(11) = (select sdt_nguoi_nhan from @temp where STT = @i)
		declare @ten_nguoi_nhan nvarchar(255) = (Select DISTINCT ten_nguoi_nhan from HoaDon where sdt_nguoi_nhan like @sdt_nguoi_nhan)
		declare @tong_gia float = (Select DISTINCT sum(tong_gia) from HoaDon where sdt_nguoi_nhan like @sdt_nguoi_nhan)
		declare @luot_mua int = (Select count(*) from HoaDon where sdt_nguoi_nhan like @sdt_nguoi_nhan)
		declare @Tong_so_san_pham_da_mua int = (Select SUM(ct.so_luong_don) from hdchitiet ct, hoadon hd 
		where ct.id_hd like hd.id_hd and hd.sdt_nguoi_nhan like @sdt_nguoi_nhan)
		insert into @value
		values (@sdt_nguoi_nhan, @ten_nguoi_nhan, @tong_gia, @luot_mua, @Tong_so_san_pham_da_mua);
		set @i = @i + 1;
	end
	Select * from @Value
end

exec proc_report1

Insert into NhanHang(ten_nhan_hang) values(N'IPhone')
Insert into NhanHang(ten_nhan_hang) values(N'Samsung')
Insert into NhanHang(ten_nhan_hang) values(N'Xiaomi')
Insert into NhanHang(ten_nhan_hang) values(N'Nokia')
Insert into NhanHang(ten_nhan_hang) values(N'Oppo')
Insert into NhanHang(ten_nhan_hang) values(N'Realme')
Insert into NhanHang(ten_nhan_hang) values(N'Vivo')
Insert into NhanHang(ten_nhan_hang) values(N'Itel')
Insert into NhanHang(ten_nhan_hang) values(N'Masstel')