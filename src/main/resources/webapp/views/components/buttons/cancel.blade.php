@props(["small"=>""])
<button onclick="window.history.back();" class="btn btn-dark ml-1 {{($small=='true')? 'btn-sm' : ''}}" type="button" >
    <i class="fas fa-undo"></i> {{ $slot }}
</button>
