@props(["small"=>""])
<button onclick="window.history.back();" class="btn btn-warning ml-1 {{($small=='true')? 'btn-sm' : ''}}" type="button"
        data-toggle="tooltip" title="{{__('Return Back')}}">
    <i class="fas fa-reply"></i> {{ $slot }}
</button>
