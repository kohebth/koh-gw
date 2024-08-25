@props(["route" => "", 'moduleName' => "", "icon" => "fas fa-plus-circle", "small" => "", "class" => ""])

@can('edit_' . str_replace('-', '_', $moduleName))
    @if($route)
        <a href='{{$route}}'
           class='btn btn-success {{($small=='true')? 'btn-sm' : ''}} {{$class}}' >
            <i class="{{$icon}}"></i>
            {{ $slot }}
        </a>
    @else
        <button type="submit"
                class='btn btn-success {{($small=='true')? 'btn-sm' : ''}} {{$class}}' >
            <i class="{{$icon}}"></i>
            {{ $slot }}
        </button>
    @endif
@endcan
