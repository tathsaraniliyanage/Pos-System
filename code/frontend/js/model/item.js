
function save_item(item_obj) {
    
    $.ajax({
        url: 'http://localhost:8080/webpos_spring_war_exploded/api/v1/item',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(item_obj),
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            alert('Error loading customer data');
        }
    });
    // item.push(item_obj);
}

function update_item(item_code) {

    $.ajax({
        url: `http://localhost:8080/webpos_spring_war_exploded/api/v1/item?code=${item_code}`,
        method: 'GET',
        success: function (data) {
            let item = data.data;

            item.code = item_code;
            item.item_name = $('#item-from-name').val();
            item.qty = $('#item-from-qty').val();
            item.price = $('#item-from-price').val();
            item.description = $('#item-from-description').val();
            item.image = $('#preview').attr('src');

            $.ajax({
                url: 'http://localhost:8080/webpos_spring_war_exploded/api/v1/item',
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(item),
                success: function (data) {
                    console.log(data);
                },
                error: function (error) {
                    alert('Error loading customer data');
                }
            });

        },
        error: function (error) {
            alert('Error loading customer data');
        }
    });

}

function delete_item(number) {

    $.ajax({
        url: `http://localhost:8080/webpos_spring_war_exploded/api/v1/item?code=${number}`,
        method: 'DELETE',
        data: JSON.stringify(item_obj),
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            alert('Error loading customer data');
        }
    });
}

function pagination(startIndex, endIndex) {
    return item.slice(startIndex, endIndex)
}

function get_items(tbody) {

    $.ajax({
        url: 'http://localhost:8080/webpos_spring_war_exploded/api/v1/item',
        method: 'GET',
        success: function (data) {
            console.log(data);
            var list=data.data;
            list.map(item => {
                var row = ` <tr data-id= ${item.code} class="shadow-sm rounded-3 mb-2" style="" >
                                <td scope="row" class="d-flex justify-content-center flex-wrap" style="width: 125px"><img src=${item.image} height="70px"></td>
                                <td>${item.code}</td>
                                <td>${item.item_name}</td>
                                <td>${item.qty}</td>
                                <td>LKR. ${item.price}</td>
                                <td>${item.description}</td>
                              </tr>`;
                tbody.append(row);
        
            });
        },
        error: function (error) {
            alert('Error loading customer data');
        }
    });
}


async function get_items_byCode(code) {

    // var item;

    // $.ajax({
    //     url: `http://localhost:8080/webpos_spring_war_exploded/api/v1/item?code=${code}`,
    //     method: 'GET',
    //     success: async function (data) {
    //         console.log(data.data)
    //         item= await data.data;
    //         console.log(item)
    //     },
    //     error: function (error) {
    //         alert('Error loading customer data');
    //     }
    // });

    // console.log(item)

    // return await item;

    console.log(code)

    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/webpos_spring_war_exploded/api/v1/item?code=${code}`,
            method: 'GET',
            success: function (data) {
                console.log(data.data);
                resolve(data.data); // Resolve the promise with the data
            },
            error: function (error) {
                alert('Error loading item data');
                reject(error); // Reject the promise in case of error
            }
        });
    });
}
