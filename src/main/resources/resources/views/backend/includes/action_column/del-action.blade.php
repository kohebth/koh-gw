@can('delete_' . str_replace('-', '_', $moduleName))
    <button wire:click="updateMultiple('delete','{{ $item->id }}')"
            onclick="return confirm('Are you sure to delete?') || event.stopImmediatePropagation()"
            class="btn btn-danger btn-sm mb-1" data-toggle="tooltip" title="{{__('labels.backend.delete')}}">
        <i class="fas fa-trash-alt"></i>
    </button>
@endcan
