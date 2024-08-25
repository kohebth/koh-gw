@props(["route" => "", 'moduleName' => "", "disabled" => "" ,"icon" => "fas fa-file-upload", "small" => "", "class" => ""])

@can("import_{$moduleName}")
    @if($route)
        <a href='{{$route}}'
           class='btn btn-primary {{($small=='true')? 'btn-sm' : ''}} {{$class}}' >
            <i class="{{$icon}}"></i>
            {{$slot}}
        </a>
    @else
        <button type="submit"
                class='btn btn-primary {{($small=='true')? 'btn-sm' : ''}} {{$class}}' {{$disabled}}>
            <i class="{{$icon}}"></i>
            {{$slot}}
        </button>
    @endif
@endcan
