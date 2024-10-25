$(document).ready(function() {
	
	//xử button left (chuyển dữ liệu bảng thuộc nhóm sang không thuộc nhóm)
    $('#moveLeftBtn').click(function() {
        const selectedRow = $('.table-in-group tbody tr.selected');
        if (selectedRow.length > 0) {
            const rowData = selectedRow.html(); 
			
            // Thay đổi giá trị của input ẩn
            $('#group').val('');

            // Xóa dòng được chọn trong bảng "Thuộc nhóm"
            selectedRow.remove();
            
            /// thêm dòng mới thay thế vào dòng đã xóa
            $('.table-in-group tbody').append('<tr>' +
				'<td>&nbsp;</td>' +
				'<td>&nbsp;</td>' +
				'</tr>');

            // Kiểm tra xem dữ liệu có tồn tại hay không
            if (rowData.trim() !== "") {
                const newRow = $('<tr>').html(rowData);
                $('.table-not-in-group tbody tr').last().remove();
                $('.table-not-in-group tbody').append(newRow);
                
                // Gán lại sự kiện click cho dòng mới
                bindClickEvent();
            }
        }
    });
    
	//xử button right (chuyển dữ liệu bảng không thuộc nhóm sang thuộc nhóm)
	$('#moveRightBtn').click(function() {
		const selectedRow = $('.table-not-in-group tbody tr.selected');
		const group = $('#group').val();

		if (selectedRow.length > 0 && group =="") {
			const rowData = selectedRow.html(); 
			selectedRow.remove()

			// Thay đổi giá trị của input ẩn
			const groupID = selectedRow.find('td:first').text().trim();
			$('#group').val(groupID);
			
			//thêm dòng trống vào bảng không thuộc nhóm
			$('.table-not-in-group tbody').append('<tr>' +
				'<td>&nbsp;</td>' +
				'<td>&nbsp;</td>' +
				'</tr>');
			
			//thêm dữ liệu mới vào bảng thuộc nhóm
			const newRow = $('<tr>').html(rowData);
			$('.table-in-group tbody').prepend(newRow);
			$('.table-in-group tbody tr').last().remove();
			
			//gán lại sự kiện cho dòng mới
			 bindClickEvent();
		} else if (selectedRow.length > 0 && group !==""){
			//xử lí dữ liệu ở không thuộc nhóm
			const rowData = selectedRow.html(); 
			selectedRow.remove()
			const groupID = selectedRow.find('td:first').text().trim();
			
			//xử lí dữ liệu đã có trong bảng thuộc nhóm
			const rowInGroup = $('.table-in-group tbody tr:first').html();
			
			// Thay đổi giá trị của input ẩn bằng dữ liệu mới
			$('#group').val(groupID);
			console.log($('#group').val())
			
			//thay thế dữ liệu mới cho bảng thuộc nhóm
			const newRow = $('<tr>').html(rowData);
			$('.table-in-group tbody tr').first().remove();
			$('.table-in-group tbody').prepend(newRow);
			
			//trả dữ liệu cũ của bảng thuộc nhóm về không thuộc nhóm
			const oldRow = $('<tr>').html(rowInGroup);
			$('.table-not-in-group tbody').prepend(oldRow);
			
			//gán lại sự kiện cho dòng mới
			bindClickEvent();
		} 
	});

    // Hàm gán sự kiện click cho các dòng trong bảng
    function bindClickEvent() {
        $('.table-not-in-group tbody tr').off('click').click(function() {
            $('.table-not-in-group tbody tr').removeClass('selected');
            
            //kiểm tra dòng có giá trị không trước khi thêm "selected"
             if($(this ).find("td").html().trim() !=="&nbsp;" ){
				$(this).addClass('selected');
			}
        });

        $('.table-in-group tbody tr').off('click').click(function() {
            $('.table-in-group tbody tr').removeClass('selected');
            
            //kiểm tra dòng có giá trị không trước khi thêm "selected"
            if($(this ).find("td").html().trim() !=="&nbsp;" ){
				$(this).addClass('selected');
			}
        });
    }

    // Gán sự kiện click ban đầu cho các dòng
    bindClickEvent();
    
    //show password
    $('#showPassword').on('change', function() {
        var passwordField = $('#inputPassword');
        var passwordField2 = $('#inputPassword2');
        var fieldType = $(this).is(':checked') ? 'text' : 'password';
        passwordField.attr('type', fieldType);
        passwordField2.attr('type', fieldType);
    });
    
    
});


//set action trong from
function setAction(action) {
	$('#action').val(action);
}
