@can('edit_' . str_replace('-', '_', $moduleName))
    <a href="{{route("backend.{$moduleName}.destroy", $item)}}" class="btn btn-danger btn-sm mb-1" data-method="DELETE"
       data-token="{{csrf_token()}}" data-toggle="tooltip" title="{{__('labels.backend.delete')}}"
       data-confirm="Are you sure to delete?"><i class="fas fa-trash-alt"></i></a>
@endcan
