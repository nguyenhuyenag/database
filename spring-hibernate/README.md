# POJO (Plain Old Java Object)

	- POJO là những class thuần túy, không triển khai bất kì framework hay technology nào
	
# JavaBean

	- Là POJO có ít nhất default constructor & implements Serializable
	
# @Entity & @Table

	- Đánh dấu 1 JavaBean trở thành một Entity
	
	- Nếu không sử dụng @Table trong Entity thì mặc định tên bảng sẽ là tên lớp của Entity
	
	- Entity khớp với một bảng lấy tên theo thứ tự ưu tiên:

		1. `name` trong @Table
		2. `name` trong @Entity
		3. `name` của class
	
# @Id

	- Đánh dấu thuộc tính primary key của entity tương ứng với cột primary key trong bảng
	
	- Một entity bắt buộc phải có ít nhất một thuộc tính primary key đi kèm với @Id

# @Transient

	- Sử dụng khi Entity chứa 1 field mà bảng không có, xem trường `company` trong `User.java`
	
	- Field được đánh dấu @Transient sẽ được JPA bỏ qua, dữ liệu load lên từ bảng sẽ là null và không save dữ liệu xuống bảng

	- Unknown column 'user0_.company' in 'field list'

	- Xem thêm tại: https://stackoverflow.com/q/1281952/7068014

# @GeneratedValue (fix): https://gpcoder.com/6338-cac-annotation-cua-hibernate/#GeneratedValue

	- Dùng để Hibernate tự động tạo ra giá trị và gán vào một cột khi insert mới một Entity

	// strategy = GenerationType.TABLE	// SEQUENCE, IDENTITY
	
	- strategy = GenerationType.AUTO: Giá trị được sinh ra bởi SEQUENCE hoặc tự tăng (nếu là IDENTITY)
	
	- GenerationType.IDENTITY: Chỉ có MySQL, DB2, SQL Server, Sybase và PostgreSQL,...hỗ trỡ. Oracle không hỗ trợ
	
# @Column
	
	- `columnDefinition`: Định nghĩa cấu trúc của một ccột, ví dụ
	
		@Column(name = "name", columnDefinition = "VARCHAR(4) NOT NULL")
		private String name;
		
		=> Khi dùng JPA Tool để tạo bảng ta sẽ nhận được: `name` VARCHAR(4) NOT NULL
	
	- `unique`: CÓ/KHÔNG thể chứa giá trị giống nhau. Mặc định là TRUE (chỉ có tác dụng bằng câu lệnh)
	
	- `nullable`: CÓ/KHÔNG thể chứa giá trị NULL (chỉ có tác dụng bằng câu lệnh)
	
	- `insertable`:	Cho phép cột insert, mặc định là TRUE, nếu FALSE sẽ báo lỗi
	
					=> `Field 'name' doesn't have a default value`
	
	- `updatable`: Cho phép cột cập nhật giá trị, mặc định là TRUE
	
	- `length`: Độ dài giá trị của cột (mặc định nó là 255)
	
	- Nếu tên trong entity và bảng giống nhau thì không cần dùng @Column
	
	- Cột kiểu int, chứa giá trị NULL khi map với class sẽ lỗi
	
# Composite Primary Key (Bảng có nhiều khóa chính)
	
	- @Embeddable
			+ Tạo class chứa các khóa chính, đánh dấu @Embeddable
			+ Nếu dùng @EmbeddedId => thì không dùng thể @Id
			+ Khi khai báo id là 1 class ta dùng annotation @EmbeddedId
	
	- @IdClass

# @OneToMany & @ManyToOne
	
	class Company {
		Integer id;
		
		@OneToMany(mappedBy = "company")
		private List<Staff> listStaff;
	}
	
	class Staff {
		@ManyToOne
		@JoinColumn(name = "company_id") // optional
		private Company company;
	}

	- @OneToMany: Một đối tượng Company có thể chứa nhiều đối tượng Staff (Collection)
	- mappedBy = "company" hiểu là mapping thông qua thuộc tính company trong Staff
	
	- @ManyToOne: Nhiều đối tượng Staff sẽ cùng thuộc một đối tượng Company
	- @JoinColumn: Chỉ rõ mapping qua cột company_id trong table staff, quy tắc tự động
		
		TenClassChuKhoaChinh_TenKhoaChinh	=> Ví dụ `company_id`
		
# @OrderBy

	- Dùng để sắp xếp một danh sách, vì vậy nó có thể được sử dụng cùng với @OneToMany, @ManyToMany
	
			@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
			@OrderBy("title")
			private Set<Post> posts;
				
# @OneToOne

	- Một bản ghi chỉ cho phép duy nhất một bản ghi khác tham chiếu tới nó
	
	- Annotation @OneToOne biểu thị mối quan hệ 1 – 1
	
	- @JoinColumn(name = "person_id") biểu thị rằng 2 đối tượng mapping qua column person_id 
	Trường hợp dùng chung id thì ta thay bằng annotation @PrimaryKeyJoinColumn
	

# @ElementCollection

	- Dùng thay thế cho @OneToMany trong những trường hợp mapping 1-nhiều mà không cần phải tạo
	  class mapping tương ứng cho tất cả các bảng.
	  
	@ElementCollection(fetch = FetchType.EAGER)		// Một đối tượng employee chứa 1 tập các position
	@Column(name = "position") 					 		// là column position trong empl_position nên cần chỉ
															// rõ ra nó sẽ nhận giá trị ở column nào bằng
	@JoinTable(									 		// cấu hình các thông tin mapping trong bảng
		name = "empl_position"				    		// bảng chứa collection
    	joinColumns = @JoinColumn(name = "empl_id")	// column thực hiện mapping tới table empl là empl_id
    )
	private List<String> list;
	
# So sánh @ElementCollection và @OneTomany

	- Đều dùng cho trường hợp mối quan hệ giữa các bảng là 1-nhiều

	- Với annotation @ElementCollection thì khi mapping entity trong class Java, ta không cần phải tạo class mapping cho phía many (phía many sẽ là kiểu dữ liệu basic hoặc là 1 class được đánh dấu @Embeddable)

	- Với annotation@OneToMany thì khi mapping entity trong class Java ta cần phải tạo cả 2 class mapping tới 2 table tương ứng cho cả phía one và phía many.

	- Annotation @ElementCollection dùng cho những trường hợp mà bên phía table many không được dùng 1 cách riêng biệt (chỉ có ý nghĩa khi gán với table phía one). Hoặc những trường hợp phía many chỉ có dữ liệu basic như int, string,...

# @ManyToMany

# @Cascade

	- Khi đối tượng bị `cascade` trong persistence context thì đối tượng tham chiếu tới nó cũng bị ảnh hưởng
	
	- Dùng trong mối quan hệ 1 - nhiều
	
	- Cú pháp: cascade = {CascadeType.REMOVE, CascadeType.PERSIST}
	
	- Ví dụ: company (1)	-> 		employee (n) 
	
		public class Company {
			//...
			@OneToMany(cascade = {CascadeType.REMOVE}, orphanRemoval = true, ...)
			private Set<Employee> listEmployee = new HashSet<>();
		}
		
		+ Với cascade = CascadeType.REMOVE, khi xóa đối tượng company thì tất cả các đối tượng employee liên quan cũng bị xóa theo (nó chỉ tác động tới các employee khi object company bị remove).
    
		+ Với thuộc tính orphanRemoval = true thì khi remove 1 đối tượng employee khỏi listEmployee thì nó sẽ bị xóa khỏi database (nó chỉ tác động tới các employee khi listEmployee thay đổi).
		
		+ Mặc định orphanRemoval = false
		
		+ cascade = {CascadeType.ALL} sẽ bao gồm cascade = {CascadeType.REMOVE}
		
		+ Nếu muốn xóa tất cả các đối tượng Employee bên trong Company với orphanRemoval = true :
			- Ta remove tất cả các object Employee trong listEmployee bằng method clear() (company.getListEmployee().clear();)
			
			- Không được set null hoặc set new HashSet() (  company.setListEmployee(new HashSet<>()); or company.setListEmployee(null);)
	
	- Các loại cascade
	
		- ALL, DETACH, MERGE, PERSIST, REFRESH, REMOVE

# FetchType

	- Định nghĩa cách lấy các đối tượng liên quan (@OneToOne, @OneToMany, @ManyToOne, @ManyToMany)

	- LAZY: Khi select đối tượng Country mặc định không query các đối tượng Province liên quan (tuy nhiên
	        trong trong transaction vẫn có, LazyInitializationException), xem log sẽ thấy
	
		Hibernate: select country0_.id as id1_3_, ...

	- EAGER: Khi select đối tượng Country mặc định select tất cả các đối tượng Province, xem log sẽ thấy
	
		Hibernate: select country0_.id as id1_3_, ...
		Hibernate: select listprovin0_.country_id as country_3_13_0_,...
		Hibernate: select listprovin0_.country_id as country_3_13_0_,...
	
	- FetchType mặc định
		
		- @ManyToOne và @OneToOne là EAGER
	
		- @ManyToMany và @OneToMany LAZY
		
	- Nên sử dụng LAZY thay vì EAGER vì lý do hiệu năng chương trình

# @Enumerated
	
	- Đánh dấu field được lưu dạng enum, khi lưu/đọc từ database ra, nó sẽ tự động lấy name của Enum đó.
	
	- Đảm bảo được giữ liệu chỉ nhận các giá trị nhất định
	
	- Nếu dữ liệu đọc lên từ database không nằm trong class enum thì sẽ báo lỗi khi convert

# @CreationTimestamp & @UpdateTimestamp

	- Tự động cập ngày thời gian khởi tạo/cập nhật đối tượng
	
	- Cột created_datetime vẫn sẽ thay đổi nếu ta update nó sang giá trị khác. Nên set
		
		@Column(name = "created_datetime", updatable = false)

# @Temporal

	Có 3 giá trị cho TemporalType:
	
	- TemporalType.DATE 		 -	Lưu trữ ngày tháng năm (bỏ đi thời gian)
	- TemporalType.TIME 		 - 	Lưu trữ thời gian (Giờ phút giây)
	- TemporalType.TIMESTAMP	 - 	Lưu trữ ngày tháng và cả thời gian

# JPA Callbacks Method

	- Dùng trong các class có đánh dấu @Entity
	
	- Định nghĩa bởi JPA nên chỉ dùng cho EntityManager, không dùng với hibernate session
	
	@PrePersist: Thực thi trước khi entity được persist (được lưu vào database) bởi method persist()

	@PostPersist: Thực thi sau khi entity được persist
	
	@PostLoad: Thực thi sau khi một entity được load vào persistence context hiện tại hoặc một entity được refreshed.
	
	@PreUpdate: Thực thi trước khi entity được update.
	
	@PostUpdate: Thực thi sau khi entity được update.
	
	@PreRemove: Thực thi trước khi entity bị xóa khỏi database bởi method remove()
	
	@PostRemove: Thực thi sau khi entity bị xóa

# Locking trong Hibernate

	- Optimistic lock: Đảm bảo nhiều giao dịch (transaction) có thể hoàn thành mà không ảnh hưởng tới nhau, các transaction tiến hành mà không cần khóa các tài nguyên lại.
	
	Trước khi commit, mỗi transaction sẽ kiểm tra lại xem dữ liệu của nó có bị transaction khác làm thay đổi không, nếu có thì sẽ quay trở lại trạng thái lúc đầu (rollback).
	
	- Pessimistic lock: Khi bắt đầu một transaction, pessimistic lock sẽ khóa dữ liệu mà nó sử dụng lại và chỉ mở khóa khi nó đã sử dụng xong.
	
	- Khi một entity bị khoá với pessimistic lock, nó sẽ có 2 loại khoá như sau:

		+ shared lock: Chỉ có thể đọc nhưng không thể xoá, cập nhật dữ liệu.
		+ exclusive lock: Có thể xoá hoặc cập nhật dữ liệu .

	- Lock Modes
	
		+ PESSIMISTIC_READ (shared lock): Chỉ có thể đọc dữ liệu, dùng khi chỉ muốn đọc dữ liệu mà không bị làm phiền bởi các transaction khác.
		
		+ PESSIMISTIC_WRITE (exclusive lock): Có toàn quyền đọc, xoá, chỉnh sửa dữ liệu và ngăn không cho các transaction khác đọc, xoá, và cập nhật dữ liệu lên chúng.
		
		+ PESSIMISTIC_FORCE_INCREMENT: Giống với PESSIMISTIC_WRITE, ngoài ra nó còn tăng giá trị của thuộc tính @Version trong entity. Một entity có thuộc tính @Version thì nên sử dụng PESSIMISTIC_FORCE_INCREMENT thay vì PESSIMISTIC_WRITE.
		
		+ Tất cả chúng đều là static members của lớp LockModeType cho phép các transaction có được database lock. Tất cả đều được giữ lại cho đến khi transaction commits hoặc rolls back.

	- Ví dụ
	
		Query query = entityManager.createQuery("from...");
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		
		Student student = entityManager.find(Student.class, id);
		entityManager.lock(student, LockModeType.OPTIMISTIC);
		
		Student student = entityManager.find(Student.class, id);
		entityManager.refresh(student, LockModeType.READ);
		
	- Spring boot Transaction Locks

		@Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
		@Query("SELECT c FROM Customer c WHERE c.orgId = ?1")
		public List<Customer> fetchCustomersByOrgId(Long orgId);
		
		@Lock(LockModeType.PESSIMISTIC_READ)
		public Optional<Customer> findById(Long customerId);
	
	- Xem Hibernate annotation @Version (Hibernate Locking Version)
