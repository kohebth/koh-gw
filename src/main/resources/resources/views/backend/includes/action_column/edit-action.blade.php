@can('edit_' . str_replace('-', '_', $moduleName))
    <a href="{{route("backend.{$moduleName}.edit", $item)}}" class="btn btn-primary btn-sm mb-1" data-toggle="tooltip"
       title="{{__('labels.backend.edit')}}"><i class="fas fa-edit"></i></a>
@endcan
