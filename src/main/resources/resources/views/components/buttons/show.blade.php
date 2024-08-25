@props(["route" => "", 'moduleName' => "", "icon" => "fas fa-eye", "title" => __('Show'), "small" => "", "class" => ""])

@can("view_{$moduleName}")
    @if($route)
        <a href='{{$route}}'
           class='btn btn-info {{($small=='true')? 'btn-sm' : ''}} {{$class}}'
           data-toggle="tooltip"
           title="{{ $title }}">
            <i class="{{$icon}}"></i>
            {{ $slot }}
        </a>
    @else
        <button type="submit"
                class='btn btn-info {{($small=='true')? 'btn-sm' : ''}} {{$class}}'
                data-toggle="tooltip"
                title="{{ $title }}">
            <i class="{{$icon}}"></i>
            {{ $slot }}
        </button>
    @endif
@endcan
