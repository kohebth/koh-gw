@can('view_' . str_replace('-', '_', $moduleName))
    <a href="{{route("backend.{$moduleName}.show", $item)}}" class="btn btn-info btn-sm mb-1" data-toggle="tooltip"
       title="{{__('labels.backend.show')}}"><i class="fas fa-eye"></i></a>
@endcan
