/**
 * 
 */
$(document).ready(function() {
	let selectedRow;

	$(document).on("contextmenu", "#serviceOfSystem tbody tr", function(event) {
		event.preventDefault();
		$("#moveOne").show();
		$("#moveAll").show();
		$("#moveBackOne").hide();
		$("#moveBackAll").hide();

		if ($(this).text().trim().length > 0) {
			selectedRow = $(this);

			const posX = event.pageX;
			const posY = event.pageY;

			$("#contextMenu").css({
				"left": posX + "px",
				"top": posY + "px"
			}).show();
		}
	});

	$(document).on("contextmenu", "#serviceOfGroup tbody tr", function(event) {
		event.preventDefault();
		$("#moveOne").hide();
		$("#moveAll").hide();
		$("#moveBackOne").show();
		$("#moveBackAll").show();

		if ($(this).text().trim().length > 0) {
			selectedRow = $(this);

			const posX = event.pageX;
			const posY = event.pageY;

			$("#contextMenu").css({
				"left": posX + "px",
				"top": posY + "px"
			}).show();
		}
	});

    $(document).click(function() {
        $("#contextMenu").hide();
    });
    
	$("#moveBackOne").click(function() {
		const hierarchy = selectedRow.find("td:first").text();
		const serviceName = selectedRow.find("td").eq(1).text();
		const groupID = selectedRow.find("td:first input").val();

		// kiểm tra vị trí của dòng thêm mới vào bảng chức năng của nhóm phân quyền
		let lastDataRow = $("#serviceOfSystem tbody tr").filter(function() {
			return $(this).find("td:first").text().trim().length > 0;
		}).last();

		if (lastDataRow.length === 0) {
			$("#serviceOfSystem tbody").prepend("<tr><td class='col-3'>"
			    +"<input type='hidden' value='" +groupID+ "'/>"
				+ serviceName
				+ "</td><td class='col-6'>"
				+ hierarchy
				+ "</td></tr>");
		} else {

			// Thêm hàng trống sau hàng dữ liệu cuối cùng
			$("<tr><td class='col-3'>"
				+"<input type='hidden' value='" +groupID+ "'/>"
				+ serviceName
				+ "</td><td class='col-6'>"
				+ hierarchy
				+ "</td></tr>").insertAfter(lastDataRow);
		}

		selectedRow.remove();
		$("#contextMenu").hide();
		$("#serviceOfGroup tbody").append("<tr>"
			+ "<td class='col-3'>&nbsp;</td> "
			+ "<td class='col-6'>&nbsp;</td> "
			+ "<td class='col-3'>&nbsp;</td> "
			+ "</tr>");
	});
	
	$("#moveBackAll").click(function() {
		$("#serviceOfGroup tbody tr").each(function() {
			if ($(this).text().trim().length > 0) {
				const hierarchy = $(this).find("td:first").text();
				const serviceName = $(this).find("td").eq(1).text();
				const groupID = $(this).find("td:first input").val();

				// kiểm tra vị trí của dòng thêm mới vào bảng chức năng của nhóm phân quyền
				let lastDataRow = $("#serviceOfSystem tbody tr").filter(function() {
					return $(this).find("td:first").text().trim().length > 0;
				}).last();

				if (lastDataRow.length === 0) {
					$("#serviceOfSystem tbody").prepend("<tr><td class='col-3'>"
						+ "<input type='hidden' value='" + groupID + "'/>"
						+ serviceName
						+ "</td><td class='col-6'>"
						+ hierarchy
						+ "</td></tr>");
				} else {

					// Thêm hàng trống sau hàng dữ liệu cuối cùng
					$("<tr><td class='col-3'>"
						+ "<input type='hidden' value='" + groupID + "'/>"
						+ serviceName
						+ "</td><td class='col-6'>"
						+ hierarchy
						+ "</td></tr>").insertAfter(lastDataRow);
				}

				$(this).remove();
				$("#contextMenu").hide();
				$("#serviceOfGroup tbody").append("<tr>"
					+ "<td class='col-3'>&nbsp;</td> "
					+ "<td class='col-6'>&nbsp;</td> "
					+ "<td class='col-3'>&nbsp;</td> "
					+ "</tr>");
			}
		})
		$("#contextMenu").hide();
	});



    $("#moveOne").click(function() {
        const hierarchy = selectedRow.find("td:last").text();
        const serviceName = selectedRow.find("td:first").text();
        const groupID = selectedRow.find("td:first input").val();
        
        //kiểm tra vị trí của dòng thêm mới vào bảng chức năng của nhóm phân quyền
		let lastDataRow = $("#serviceOfGroup tbody tr").filter(function() {
			return $(this).find("td:first").text().trim().length > 0;
		}).last();
		 if (lastDataRow.length === 0) {
            $("#serviceOfGroup tbody").prepend("<tr><td class='col-3'><input type='hidden' name='serviceGroups[0].serviceGroupID' value='" + groupID + "' />" 
              + hierarchy 
              + "</td><td class='col-6'>" 
              + serviceName 
              + "</td><td class='col-3'>"
			  +	"<label class='container' >"
			  +	"<input name='serviceGroups[0].onlyView' type='checkbox' >"
			  +  "<span class='checkmark'></span>"
			  +	"</label ></td></tr> ");
        } else {
			// Tìm số kế tiếp của lastDataRow
			var nextIndex = parseInt(lastDataRow.find("input[name^='serviceGroups']").attr("name").match(/\[(\d+)\]/)[1]) + 1;
			
            // Thêm hàng trống sau hàng dữ liệu cuối cùng
            $("<tr><td class='col-3'><input type='hidden' name='serviceGroups[" + nextIndex + "].serviceGroupID' value='" + groupID + "' />" 
              + hierarchy 
              + "</td><td class='col-6'>" 
              + serviceName 
              + "</td><td class='col-3'>"
			  +	"<label class='container' >"
			  +	"<input name='serviceGroups[" + nextIndex + "].onlyView' type='checkbox' >"
			  +  "<span class='checkmark'></span>"
			  +	"</label ></td></tr> ").insertAfter(lastDataRow);
             
        }
        selectedRow.remove();
        $("#contextMenu").hide();
		$("#serviceOfSystem tbody").append("<tr>"
			+ "<td class='col-2'>&nbsp;</td> "
			+ "<td class='col-10'>&nbsp;</td> "
			+ "</tr>");
    });

	$("#moveAll").click(function() {
		$("#serviceOfSystem tbody tr").each(function() {
			if ($(this).text().trim().length > 0) {
				const hierarchy = $(this).find("td:last").text();
				const serviceName = $(this).find("td:first").text();
				const groupID = $(this).find("td:first input").val();
				
				let lastDataRow = $("#serviceOfGroup tbody tr").filter(function() {
					return $(this).find("td:first").text().trim().length > 0;
				}).last();
				if (lastDataRow.length === 0) {
					$("#serviceOfGroup tbody").prepend("<tr><td class='col-3'><input type='hidden' name='serviceGroups[0].serviceGroupID' value='" + groupID + "' />"
						+ hierarchy
						+ "</td><td class='col-6'>"
						+ serviceName
						+ "</td><td class='col-3'>"
						+ "<label class='container' >"
						+ "<input name='serviceGroups[0].onlyView' type='checkbox' >"
						+ "<span class='checkmark'></span>"
						+ "</label ></td></tr> ");
				} else {
					// Tìm số kế tiếp của lastDataRow
					var nextIndex = parseInt(lastDataRow.find("input[name^='serviceGroups']").attr("name").match(/\[(\d+)\]/)[1]) + 1;
					// Thêm hàng trống sau hàng dữ liệu cuối cùng
					$("<tr><td class='col-3'><input type='hidden' name='serviceGroups[" + nextIndex + "].serviceGroupID' value='" + groupID + "' />"
						+ hierarchy
						+ "</td><td class='col-6'>"
						+ serviceName
						+ "</td><td class='col-3'>"
						+ "<label class='container' >"
						+ "<input name='serviceGroups[" + nextIndex + "].onlyView' type='checkbox' >"
						+ "<span class='checkmark'></span>"
						+ "</label ></td></tr> ").insertAfter(lastDataRow);
				}
				$(this).remove();
				$("#serviceOfSystem tbody").append("<tr>"
					+ "<td class='col-2'>&nbsp;</td> "
					+ "<td class='col-10'>&nbsp;</td> "
					+ "</tr>");
			}
		});
		$("#contextMenu").hide();
	});
	
	//Hiển thị thêm nhóm mới 
	$('#openFormBtn').click(function() {
		$('#addNewModal').modal('show');
	});

	//Đóng thêm nhóm mới
	$("#closeModalAddGroupnew").click(function() {
		$("#addNewModal").modal('hide');
	})
	
	//hiển thị dòng thông báo update thành công
	var alertMessage = $('#alertMessage');
	if (alertMessage.length) {
		alertMessage.fadeIn('slow');
		setTimeout(function() {
			alertMessage.fadeOut('slow');
		}, 3000);
	}
	
	// xử lí nút button đóng lại
	$('.close').click(function() {
		$(".confirmClose").show();
	});
	
	$(".cancel-button").click(function(){
		$(".confirmClose").hide();
	})
	
})
