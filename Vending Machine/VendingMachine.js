$(document).ready(function() {
  loadItems();
})

function loadItems(){
  $("#items").empty();
  var allItems = $("#items");

  $.ajax({
    type: "GET",
    url: "http://tsg-vending.herokuapp.com/items",

    success:function(itemsArray){
      $.each(itemsArray, function(index,item){
          var itemId = item.id;
          var name = item.name;
          var price = item.price;
          var quantity = item.quantity;

          var row = "<div class='card col-md-4' style = 'float:left'";
          row += "onclick ='showItem("+itemId+")'>";
          row += '<label>'+ itemId + '</label>';
          row += '<label>' + name + '</label>';
          row += '<label>' + price + '</label>';
          row += '<label>' + quantity + '</label>';
          row += '</div>';

          allItems.append(row);

      });

    },

    error:function(){
      alert("Could not load items");

    }
  });
}
    function addMoney(value){
    $('#message').val('');
      $('#change').val('');
      var moneyIn = $('#moneyIn').val();
      var total = parseFloat(moneyIn) + parseFloat(value);
      $('#moneyIn').val(total.toFixed(2));
    }

    function showItem(itemId){
        $('#itemShow').val(itemId);
      }

    function makePurchase(){
  $.ajax({
      type:'POST',
      url: 'http://tsg-vending.herokuapp.com/money/'+ $('#moneyIn').val()+'/item/'+ $('#itemShow').val(),


      success: function(change){
            $("#message").val("Thank you!!!");
            loadItems();
            $('#itemShow').val('');
            $('#moneyIn').val("0");

            var changeOutput = "Quarters:" + change.quarters;
            changeOutput +="  Dimes:" + change.dimes;
            changeOutput += " Nickel:" + change.nickels;
            changeOutput +="  Pennies:" + change.pennies;

            $('#change').val(changeOutput);


          },

          error:function(message){
          $('#message').val(message.responseJSON.message);
          }

          });
}
          function changeOut(){
          var moneyIn = $('#moneyIn').val();
          quarters = moneyIn / .25 ;
          remainder = moneyIn % .25;
          dimes = remainder / .10;
          remainder= remainder %.10;
          nickels = remainder /.05;
          remainder = remainder %.05;
          pennies = remainder;

          var changes = "Quarters: " + quarters.toFixed(0);
              changes += "   Dimes:" + dimes.toFixed(0);
              changes += "  Nickels:" + nickels.toFixed(0);
              changes += "   Pennies:" + pennies.toFixed(0);
          $('#change').val(changes);
          $('#moneyIn').val("0");

}
